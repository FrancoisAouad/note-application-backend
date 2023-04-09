package app.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface AuthRepository extends JpaRepository<AuthModel, Long> {
    Logger logger = LoggerFactory.getLogger(AuthRepository.class);

    Optional<AuthModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
