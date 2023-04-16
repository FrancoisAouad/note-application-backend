package app.auth.jwt;

// Spring

import app.auth.dto.Tokens;
import app.auth.jwt.accessTokens.AccessTokenModel;
import app.auth.jwt.accessTokens.AccessTokenRepository;
import app.auth.jwt.refreshTokens.RefreshTokenModel;
import app.auth.jwt.refreshTokens.RefreshTokenRepository;
import app.utils.GlobalService;

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
import app.auth.UserModel;

@Service
public class JwtService {
    private AccessTokenRepository accessTokenRepository;
    private RefreshTokenRepository refreshTokenRepository;
    private TokenRepository tokenRepository;
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    public JwtService(AccessTokenRepository accessTokenRepository, RefreshTokenRepository refreshTokenRepository,
            TokenRepository tokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenRepository = tokenRepository;

    }

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

    public String generateJwtToken(UserModel user, JWT_TYPE type) {
        String tokenExp;
        String tokenSecret;
        switch (type) {
            case ACCESS_TOKEN -> {
                tokenExp = jwtAccessTokenExp + "";
                tokenSecret = jwtAccessTokenSecret;
            }
            case REFRESH_TOKEN -> {
                tokenExp = jwtRefreshTokenExp + "";
                tokenSecret = jwtRefreshTokenSecret;
            }
            case EMAIL_TOKEN -> {
                tokenExp = jwtEmailTokenExp + "";
                tokenSecret = jwtEmailTokenSecret;
            }
            default -> throw new IllegalArgumentException("Invalid JWT_TYPE value");
        }
        Map<String, String> jwtProperties = new HashMap<>();
        jwtProperties.put("id", user.getId());
        jwtProperties.put("token_secret", tokenSecret);
        jwtProperties.put("token_expiry", tokenExp);
        return generateToken(jwtProperties.get("id"), jwtProperties.get("token_secret"),
                jwtProperties.get("token_expiry"));
    }

    public String generateToken(String id, String tokenSecret, String expiryDate) {
        // String jwtId = UUID.randomUUID().toString();
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + Long.parseLong(expiryDate));
        return Jwts.builder()
                .setSubject(id)
                .setIssuer("note-application")
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    // public String getUserNameFromJwtToken(String token) {
    // return
    // Jwts.parser().setSigningKey(jwtRefreshTokenSecret).parseClaimsJws(token).getBody().getSubject();
    // }

    // public String getUserIdFromJwtToken(String token) {
    // System.out.println("BEFORE");
    // String claims =
    // Jwts.parser().setSigningKey(jwtRefreshTokenSecret).parseClaimsJws(token).getBody().getSubject();
    // System.out.println("YYESSSS" + claims);
    // return claims;
    // }

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

    /**
     * @function saveTokens - Method that creates the tokens and saves them in their
     *           tables and sends them back as response
     * @param userModel
     * @param mailToken
     * @returns Tokens - Object token that has the pair tokens
     */
    public Tokens saveTokens(UserModel userModel, Boolean mailToken) {
        if (mailToken) {
            String emailToken = generateJwtToken(userModel, JWT_TYPE.EMAIL_TOKEN);
            userModel.setEmailToken(emailToken);
        }
        String accessToken = generateJwtToken(userModel, JWT_TYPE.ACCESS_TOKEN);
        String refreshToken = generateJwtToken(userModel, JWT_TYPE.REFRESH_TOKEN);
        AccessTokenModel accessTokenModel = AccessTokenModel.builder()
                .id(GlobalService.generateUUID())
                .accessTokenValue(accessToken)
                .build();
        accessTokenRepository.save(accessTokenModel);
        RefreshTokenModel refreshTokenModel = RefreshTokenModel.builder()
                .id(GlobalService.generateUUID())
                .refreshTokenValue(refreshToken)
                .build();
        refreshTokenRepository.save(refreshTokenModel);
        TokenModel tokenModel = TokenModel.builder()
                .id(GlobalService.generateUUID())
                .accessToken(accessTokenModel.getId())
                .refreshToken(refreshTokenModel.getId())
                .build();
        tokenModel.setUserId(userModel.getId());
        tokenRepository.save(tokenModel);
        return new Tokens(accessToken, refreshToken);
    }
}
