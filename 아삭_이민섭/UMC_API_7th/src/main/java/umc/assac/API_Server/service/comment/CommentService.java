package umc.assac.API_Server.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.assac.API_Server.domain.board.Board;
import umc.assac.API_Server.domain.comment.Comment;
import umc.assac.API_Server.domain.user.User;
import umc.assac.API_Server.dto.comment.CommentRequestDto;
import umc.assac.API_Server.exception.board.BoardNotFoundException;
import umc.assac.API_Server.exception.comment.CommentNotFoundException;
import umc.assac.API_Server.exception.user.UserWriterException;
import umc.assac.API_Server.repository.board.BoardRepository;
import umc.assac.API_Server.repository.comment.CommentRepository;

// 댓글과 관련된 로직들을 수행
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 댓글 생성
    @Transactional
    public void makeComment(CommentRequestDto requestDto, Long boardId, User user) {
        Board findItem = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        Comment comment = Comment.getComment(requestDto.getContent(), user.getUsername(), findItem);

        commentRepository.save(comment);
    }

    // 댓글 수정
    @Transactional
    public void editComment(Long commentId, CommentRequestDto createDto, User user) {
        Comment findItem = getValidComment(commentId, user);
        findItem.updateComment(createDto.getContent());
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, User user) {
        Comment findItem = getValidComment(commentId, user);
        commentRepository.delete(findItem);
    }

    // 유효성을 판단하여 Comment를 반환해주는 메소드
    public Comment getValidComment(long commentId, User user) {
        Comment findItem = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        if(!validateUser(findItem, user)) throw new UserWriterException();
        return findItem;
    }

    // 댓글을 수정, 삭제하는 로직을 수행하기 전에 로직을 수행하는 이용자와
    // 댓글을 작성한 작성자의 정보가 일치하는지를 확인하는 로직
    public boolean validateUser(Comment comment, User user) {
        String writer = comment.getWriter();
        return writer.equals(user.getUsername());
    }
}
