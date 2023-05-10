package umc.assac.API_Server.service.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.assac.API_Server.domain.board.Board;
import umc.assac.API_Server.domain.category.Category;
import umc.assac.API_Server.domain.comment.Comment;
import umc.assac.API_Server.domain.user.User;
import umc.assac.API_Server.dto.board.BoardCreateDto;
import umc.assac.API_Server.dto.board.BoardDetailedResponseDto;
import umc.assac.API_Server.dto.board.BoardEditDto;
import umc.assac.API_Server.dto.board.BoardResponseDto;
import umc.assac.API_Server.dto.page.pagingResponseDto;
import umc.assac.API_Server.dto.comment.CommentDto;
import umc.assac.API_Server.exception.board.BoardNotFoundException;
import umc.assac.API_Server.exception.board.BoardReservedException;
import umc.assac.API_Server.exception.comment.CommentNotFoundException;
import umc.assac.API_Server.exception.user.UserWriterException;
import umc.assac.API_Server.exception.category.CategoryNotFoundException;
import umc.assac.API_Server.repository.board.BoardRepository;
import umc.assac.API_Server.repository.category.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

// 게시글과 관련된 로직을 수행하는 BoardService
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    // 특정 카테고리의 게시글들을 모두 조회하는 메소드
    @Transactional(readOnly = true)
    public pagingResponseDto findBoards(Long categoryId, int page, Pageable pageable) {
        Page<Board> boards = makeBoardPage(categoryId, page, pageable);

        List<BoardResponseDto> result = boards.stream()
                .map(BoardResponseDto::toDto).collect(Collectors.toList());

        return new pagingResponseDto().toDto(result, boards);
    }

    // 특정 게시글에 작성된 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentDto> getComments(Long boardId) {
        Board findItem = getBoard(boardId);

        List<Comment> comments = findItem.getComments();
        if(comments.isEmpty()) throw new CommentNotFoundException();

        return comments.stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
    }

    // 특정 게시글만을 조회하는 로직
    @Transactional
    public BoardDetailedResponseDto findBoard(Long boardId) {
        Board findItem = getBoard(boardId);
        findItem.updateBoard();

        return BoardDetailedResponseDto.toDto(findItem);
    }

    // 게시글을 생성하는 로직
    @Transactional
    public void makeBoard(BoardCreateDto createDto, Long categoryId, User user) {
        Category findItem = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        Board board = Board.getBoard(createDto, findItem, user);

        boardRepository.save(board);
    }

    // 게시글을 수정하는 로직
    @Transactional
    public void editBoard(BoardEditDto editDto, Long boardId, User user) {
        Board findItem = getBoard(boardId);
        if(!validateUser(findItem, user)) throw new UserWriterException();
        findItem.updateBoard(editDto.getContent());
    }

    // 게시글을 삭제하는 로직
    @Transactional
    public void deleteBoard(Long boardId, User user) {
        Board findItem = getBoard(boardId);
        if(!validateUser(findItem, user)) throw new UserWriterException();
        boardRepository.delete(findItem);
    }

    // 게시글을 예약하는 로직
    @Transactional
    public void reserveBoard(Long boardId) {
        Board findItem = getBoard(boardId);
        if(findItem.isReserved()) throw new BoardReservedException();
        findItem.reserveBoard();
    }

    // 게시글의 PK를 통하여 게시글을 찾아오는 메서드
    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
    }

    // 게시글 목록의 페이징 처리를 수행하는 메서드
    public Page<Board> makeBoardPage(Long categoryId, int page, Pageable pageable) {
        pageable.withPage(page);
        Page<Board> boards = boardRepository.findAllByCategoryId(categoryId, pageable);

        if(boards.isEmpty()) throw new BoardNotFoundException();

        return boards;
    }

    // 게시글의 작성자와 현재 로직을 수행하고 있는 사용자의 정보를 비교하여
    // 작성자가 아닌 사용자가 수정이나 삭제를 수행하려는 경우 오류 반환
    public boolean validateUser(Board board, User user) {
        String writer = board.getWriter();
        return writer.equals(user.getUsername());
    }
}
