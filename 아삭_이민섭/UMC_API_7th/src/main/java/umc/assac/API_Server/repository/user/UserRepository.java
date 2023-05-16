package umc.assac.API_Server.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.assac.API_Server.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsernameOrEmail(String username, String email);
}
