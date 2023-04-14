package app.auth;

import app.global.ThrowableHttpException;
import org.springframework.data.jpa.repository.JpaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Logger logger = LoggerFactory.getLogger(UserRepository.class);

    UserModel findById(long id);

    UserModel findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    default String getUserHashedPassword(long id) throws ThrowableHttpException {
        UserModel user = findById(id);
        if (user == null) {
            throw new ThrowableHttpException(404, "User Not Found");
        }
        return user.getPassword();
    };

}
