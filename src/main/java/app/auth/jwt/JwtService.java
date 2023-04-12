package app.auth.jwt;

// Spring

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// Utils
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.jsonwebtoken.*;
// Services
import app.auth.AuthModel;


@Service
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${app.jwtAccessTokenSecret}")
    private String jwtAccessTokenSecret;
    @Value("${app.jwtRefreshTokenSecret}")
    private String jwtRefreshTokenSecret;
    @Value("${app.jwtEmailTokenSecret}")
    private String jwtEmailTokenSecret;
    @Value("${app.jwtAccessTokenExp}")
    private int jwtAccessTokenExp;
    @Value("${app.jwtRefreshTokenExp}")
    private int jwtRefreshTokenExp;
    @Value("${app.jwtEmailTokenExp}")
    private int jwtEmailTokenExp;

    public String generateJwtToken(AuthModel user, JWT_TYPE type) {
        Map<String, String> jwtProperties = new HashMap<>();
        jwtProperties.put("id", user.getUsername());
        switch (type) {
            case ACCESS_TOKEN -> {
                final String tokenExp = jwtAccessTokenExp + "";
                jwtProperties.put("token_secret", jwtAccessTokenSecret);
                jwtProperties.put("token_expiry", tokenExp);
            }
            case REFRESH_TOKEN -> {
                final String tokenExp = jwtRefreshTokenExp + "";
                jwtProperties.put("token_secret", jwtRefreshTokenSecret);
                jwtProperties.put("token_expiry", tokenExp);
            }
            case EMAIL_TOKEN -> {
                final String tokenExp = jwtEmailTokenExp + "";
                jwtProperties.put("token_secret", jwtEmailTokenSecret);
                jwtProperties.put("token_expiry", tokenExp);
            }
        }
        return generateTokenFromUsername(jwtProperties.get("id"), jwtProperties.get("token_secret"), jwtProperties.get("token_expiry"));
    }

    public String generateTokenFromUsername(String username, String token, String expiryDate) {

        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + Integer.parseInt(expiryDate)))
                .signWith(SignatureAlgorithm.HS512, token)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtRefreshTokenSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtAccessTokenSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
