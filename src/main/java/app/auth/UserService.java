package app.auth;

import java.util.Date;

import app.auth.dto.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.RequestBody;
import app.auth.dto.RegisterUserDto;
import app.auth.jwt.JwtService;
import app.global.HttpException;
import app.auth.dto.LoginDto;
import org.slf4j.Logger;
import app.auth.jwt.JWT_TYPE;
import org.slf4j.LoggerFactory;
// import app.global.Roles;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
            String accessToken = jwtService.generateJwtToken(userModel, JWT_TYPE.ACCESS_TOKEN);
            String refreshToken = jwtService.generateJwtToken(userModel, JWT_TYPE.REFRESH_TOKEN);
            String emailToken = jwtService.generateJwtToken(userModel, JWT_TYPE.EMAIL_TOKEN);
            savedUser.setEmailToken(emailToken);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Tokens(accessToken, refreshToken));
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
        String accessToken = jwtService.generateJwtToken(userModel, JWT_TYPE.ACCESS_TOKEN);
        String refreshToken = jwtService.generateJwtToken(userModel, JWT_TYPE.REFRESH_TOKEN);
        return ResponseEntity.status(HttpStatus.OK).body(new Tokens(accessToken, refreshToken));
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
