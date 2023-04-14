package app.auth;

import java.util.Date;

import app.auth.dto.Tokens;
import app.auth.jwt.TokenRepository;
import app.auth.jwt.accessTokens.AccessTokenRepository;
import app.auth.jwt.refreshTokens.RefreshTokenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import app.auth.dto.RegisterUserDto;
import app.auth.jwt.JwtService;
import app.global.HttpException;
import app.auth.dto.LoginDto;
import org.slf4j.Logger;
import app.auth.jwt.JWT_TYPE;
import org.slf4j.LoggerFactory;
import app.auth.jwt.TokenModel;
import app.auth.jwt.accessTokens.AccessTokenModel;
import app.auth.jwt.refreshTokens.RefreshTokenModel;

@Service
public class UserService {
    private UserRepository userRepository;
    private JwtService jwtService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public ResponseEntity<?> register(RegisterUserDto user) {
        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new HttpException(409, "User already exists"));
        }
        UserModel userModel = UserModel.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getUsername())
                .password(user.getPassword())
                .isVerified(false)
                .isAdmin(false)
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
        logger.debug("model for users" + userModel);
        UserModel savedUser = userRepository.save(userModel);
        if (savedUser.getId() == null) {
            return ResponseEntity.badRequest().body("Validation error");
        }
        try {
              Tokens tokens = jwtService.saveTokens(userModel, true);
            return ResponseEntity.status(HttpStatus.CREATED).body(tokens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HttpException(400, "Invalid tokens"));
        }

    }

    public ResponseEntity<?> login(LoginDto loginDto) {
        String plainPassword = loginDto.getPassword();
        String username = loginDto.getUsername();
        UserModel userModel = userRepository.findByUsername(username);
        if (userModel == null || !userModel.getPassword().equals(plainPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new HttpException(401, "Invalid username/password"));
        }
        try {
            Tokens tokens = jwtService.saveTokens(userModel, false);
            logger.info("User login is success " + tokens.accessToken + " refresh " + tokens.refreshToken);
            return ResponseEntity.status(HttpStatus.OK).body(tokens);

        } catch (Exception e) {
            logger.error("Failed to login user with the following credentials: Username: '" + username
                    + "'. Password: '" + plainPassword + "'.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HttpException(400, "Invalid tokens"));
        }
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
