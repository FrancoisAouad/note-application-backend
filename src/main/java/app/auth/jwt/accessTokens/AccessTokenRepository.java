package app.auth.jwt.accessTokens;

import org.springframework.data.jpa.repository.JpaRepository;
//import app.global.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface AccessTokenRepository extends JpaRepository<AccessTokenModel, Long> {
    Logger logger = LoggerFactory.getLogger(app.auth.UserRepository.class);
}
