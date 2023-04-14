package app.auth.jwt;

// Utils
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "access_token_id", nullable = false)
    private String accessToken;
    @Column(name = "refresh_token_id", nullable = false)
    private String refreshToken;
    @Column(name = "user_id", nullable = false)
    private String userId;

}
