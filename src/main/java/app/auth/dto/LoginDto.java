package app.auth.dto;

// import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginDto {
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 8, max = 32)
    private String password;
}
