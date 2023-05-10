package umc.assac.API_Server.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.assac.API_Server.domain.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByCategoryId(Long categoryId, Pageable pageable);
    Page<Board> findAllByWriter(String writer, Pageable pageable);
}
