package app.auth.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenModel, Long> {
    Logger logger = LoggerFactory.getLogger(app.auth.UserRepository.class);
}
