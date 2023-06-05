package app.auth;

// Spring

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// Services
import app.auth.dto.RegisterUserDto;
import app.auth.dto.LoginDto;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto user) {
        ResponseEntity<?> result = userService.register(user);
        Cookie cookie = (Cookie) result.getBody();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        assert response != null;
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        ResponseEntity<?> result = userService.login(loginDto);
        Cookie cookie = (Cookie) result.getBody();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        if (result != null) {
            System.out.println("COOKIE: " + result);
            response.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid login credentials");
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
