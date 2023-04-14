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
// Services
import app.auth.dto.RegisterUserDto;
import app.auth.dto.LoginDto;
import app.global.HttpException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto user) {
        System.out.println("USER REQUST " + user);
        ResponseEntity<?> result = userService.register(user);
        System.out.println("RESULT:" + result);
        return HttpException.handleResponse(result.getStatusCodeValue(), result.getBody());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        ResponseEntity<?> result = userService.login(loginDto);
        return HttpException.handleResponse(result.getStatusCodeValue(), result.getBody());
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
