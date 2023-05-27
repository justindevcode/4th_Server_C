package umc.assac.API_Server.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.assac.API_Server.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    boolean existsUserByUsernameOrEmail(String username, String email);
}
