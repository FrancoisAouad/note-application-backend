package app.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import app.global.HttpException;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface AuthRepository extends JpaRepository<AuthModel, Long> {
    Logger logger = LoggerFactory.getLogger(AuthRepository.class);

    AuthModel findById(long id);

    AuthModel findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    default String getUserHashedPassword(long id) throws HttpException {
        AuthModel user = findById(id);
        if (user == null) {
            throw new HttpException(404, "User Not Found");
        }
        return user.getPassword();
    };

}
