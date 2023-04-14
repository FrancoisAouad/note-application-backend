package app.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Tokens {
    private String accessToken;

    private String refreshToken;

}
