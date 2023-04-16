package app.auth.jwt.accessTokens;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "access_tokens")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenModel {
    @Id
    private String id;

    @Column(name = "access_token_value")
    private String accessTokenValue;
}
