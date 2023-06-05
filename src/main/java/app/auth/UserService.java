package app.auth;

import java.util.Date;

import app.auth.dto.Tokens;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import app.auth.dto.RegisterUserDto;
import app.auth.jwt.JwtService;
import app.global.exceptions.HttpException;
import app.auth.dto.LoginDto;
import org.slf4j.Logger;
import app.utils.GlobalService;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
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
                .id(GlobalService.generateUUID())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .isVerified(false)
                .isAdmin(false)
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
        logger.debug("model for users" + userModel);
        try {
            Tokens tokens = jwtService.saveTokens(userModel, true);
            UserModel savedUser = userRepository.save(userModel);
            if (savedUser.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error");
            }
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
            Cookie cookie = new Cookie("accessToken", tokens.getAccessToken());
            // setCookie(cookie);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(86400);
            return ResponseEntity.status(HttpStatus.OK).body(cookie);
        } catch (Exception e) {
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
