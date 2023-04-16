package app.auth.jwt;

// Utils
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
// Lombok
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jwt_tokens")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenModel {
    @Id
    private String id;
    @Column(name = "access_token_id")
    private String accessToken;
    @Column(name = "refresh_token_id")
    private String refreshToken;
    @Column(name = "user_id")
    private String userId;

}
