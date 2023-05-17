package umc.test.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.test.board.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

}
