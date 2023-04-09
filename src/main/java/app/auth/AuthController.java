package app.auth;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.auth.dto.RegisterUserDto;
import app.global.HttpException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto user) {

        ResponseEntity<?> result = authService.register(user);
        System.out.println("RESULT IS " + result);
        if (result.getStatusCodeValue() == 400) {
            return ResponseEntity.badRequest().body(new HttpException(400, "Validation Error"));
        }
        return result;
    }

    @PostMapping("/login")
    public void login() {
        // return authService.create(category);
    }

    @PostMapping("/refresh-token")
    public void refreshToken() {
        // return authService.create(category);
    }

    @DeleteMapping("/logout")
    public void logout() {
        // return authService.create(category);
    }

    @PostMapping("/forgot-password")
    public void forgotPassword() {
        // return authService.create(category);
    }

    @PatchMapping("/reset-password/{token}")
    public void resetPassword() {
        // return authService.create(category);
    }

    @GetMapping("verify")
    public void verifyEmail() {
        // return authService.create(category);
    }
}
