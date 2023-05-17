package umc.test.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.test.board.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
