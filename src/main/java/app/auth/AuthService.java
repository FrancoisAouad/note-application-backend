package app.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.RequestBody;
import app.auth.dto.RegisterUserDto;
import app.auth.jwt.JwtService;
import app.global.HttpException;
import app.global.dto.LoginDto;
import org.slf4j.Logger;
import app.auth.jwt.JWT_TYPE;
import org.slf4j.LoggerFactory;
// import app.global.Roles;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtService jwtService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AuthService() {
    }

    ;

    public ResponseEntity<?> register(RegisterUserDto user) {
        logger.debug("BODY REQUEST: " + user);
        if (authRepository.existsByUsername(user.getUsername())) {
            // make a custom Auth exception class that will throw messages related to this
            // service
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        if (authRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthModel());
        }
        AuthModel userModel = AuthModel.builder()
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
        AuthModel savedUser = authRepository.save(userModel);
        if (savedUser.getId() == null) {
            return ResponseEntity.badRequest().body("Validation error");
        }
        String accessToken = jwtService.generateJwtToken(userModel, JWT_TYPE.ACCESS_TOKEN);
        String refreshToken = jwtService.generateJwtToken(userModel, JWT_TYPE.REFRESH_TOKEN);
        String emailToken = jwtService.generateJwtToken(userModel, JWT_TYPE.EMAIL_TOKEN);
        savedUser.setEmailToken(emailToken);
        logger.debug("savedUser" + savedUser);
        logger.debug("access:" + accessToken);
        logger.debug("refresh:" + refreshToken);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Tokens(accessToken, refreshToken));

    }

    public ResponseEntity<?> login(LoginDto login) {
        String plainPassword = login.getPassword();
        String username = login.getUsername();
        AuthModel userModel = authRepository.findByUsername(username);
//        if(userModel==null){
//            System.out.println(passwordEncoder.encode(plainPassword));
//            return ResponseEntity.ok().body(null);
//        }
        String savedPassword = userModel.getPassword();
// PasswordEncoder passwordEncoder = new PasswordEncoder();
//        if (passwordEncoder.matches(plainPassword, savedPassword)) {
//            System.out.println("yeessssssss");
//            String token = jwtService.generateJwtToken(userModel);
//            return ResponseEntity.ok().body(token);
//        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new HttpException(401, "Invalid username/password"));

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
