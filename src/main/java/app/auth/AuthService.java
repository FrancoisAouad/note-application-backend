package app.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.RequestBody;
// import app.auth.AuthRepository;
import app.auth.dto.RegisterUserDto;
import app.auth.jwt.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import app.global.Roles;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtService jwtService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<?> register(RegisterUserDto user) {
        logger.debug("BODY REQUEST: " + user);
        if (authRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthModel());
        }

        if (authRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthModel());
        }
        AuthModel userModel = AuthModel.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getUsername())
                .isVerified(false)
                .isAdmin(false)
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
        logger.debug("model for users" + userModel);
        AuthModel savedUser = authRepository.save(userModel);
        if (savedUser.getId() == null) {
            logger.debug("qqqqqqqqqqqqqqqqqqqqqq");
            return ResponseEntity.badRequest().body("Validation error");
        }
        String token = jwtService.generateJwtToken(userModel);
        savedUser.setEmailToken(token);
        logger.debug("savedUser" + savedUser);
        logger.debug("token:" + token);

        return ResponseEntity.ok(savedUser);

    }

    public void login() {
    }

    public void refreshToken() {
    }

    public void logout() {
    }

    public void forgotPassword() {
    }

    public void resetPassword() {
    }

    public void verifyEmail() {
    }
}
