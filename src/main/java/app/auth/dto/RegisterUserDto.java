package app.auth.dto;

// Lombok
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegisterUserDto {
    @NotBlank
    @Size(min = 2, max = 40)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 40)
    private String lastName;

    @NotBlank
    @Size(max = 16)
    private String username;

    @Email
    @NotBlank
    @Size(max = 40)
    private String email;

    @NotBlank
    @Size(min = 8, max = 120)
    private String password;

    @NotBlank
    @Size(min = 8, max = 120)
    private String confirmPassword;

    private boolean isAdmin;

    @AssertTrue(message = "Passwords do not match")
    private boolean isPasswordMatching() {
        return password.equals(confirmPassword);
    }
}
