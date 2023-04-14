package app.auth.jwt.refreshTokens;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenModel, Long> {
    Logger logger = LoggerFactory.getLogger(app.auth.UserRepository.class);
}