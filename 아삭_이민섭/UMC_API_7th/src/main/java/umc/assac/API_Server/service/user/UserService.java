package umc.assac.API_Server.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.assac.API_Server.domain.board.Board;
import umc.assac.API_Server.domain.comment.Comment;
import umc.assac.API_Server.domain.user.User;
import umc.assac.API_Server.dto.board.BoardResponseDto;
import umc.assac.API_Server.dto.comment.CommentDto;
import umc.assac.API_Server.dto.page.pagingResponseDto;
import umc.assac.API_Server.dto.user.UserCreateDto;
import umc.assac.API_Server.dto.user.UserDeleteDto;
import umc.assac.API_Server.dto.user.UserInfoDto;
import umc.assac.API_Server.exception.board.BoardNotFoundException;
import umc.assac.API_Server.exception.comment.CommentNotFoundException;
import umc.assac.API_Server.exception.user.UserDuplicateException;
import umc.assac.API_Server.exception.user.UserInfoException;
import umc.assac.API_Server.exception.user.UserReportedException;
import umc.assac.API_Server.repository.board.BoardRepository;
import umc.assac.API_Server.repository.comment.CommentRepository;
import umc.assac.API_Server.repository.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

// 회원과 관련된 로직들을 수행
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    // 회원 등록
    @Transactional
    public void saveUser(UserCreateDto userCreateDto) {
        validateUser(userCreateDto);
        User user = User.getUser(userCreateDto);
        userRepository.save(user);
    }

    // 회원 삭제
    @Transactional
    public void deleteUser(User user, UserDeleteDto deleteDto) {
        validateUser(user, deleteDto);
        userRepository.delete(user);
    }

    // 회원 정보 조회
    @Transactional
    public UserInfoDto getUserInfo(User user) {
        return UserInfoDto.toDto(user);
    }

    @Transactional
    public void reportUser(User user) {
        if(user.isReport()) throw new UserReportedException();
        user.reportUser();
    }

    // 회원 작성 게시글 조회
    public pagingResponseDto getUserBoards(User user, int page, Pageable pageable) {
        Page<Board> boards = getBoardPage(user.getUsername(), page, pageable);
        List<BoardResponseDto> result = boards.stream().map(BoardResponseDto::toDto).collect(Collectors.toList());

        return new pagingResponseDto().toDto(result, boards);
    }

    // 회원 작성 댓글 조회
    public pagingResponseDto getUserComments(User user, int page, Pageable pageable) {
        Page<Comment> comments = getCommentPage(user.getUsername(), page, pageable);
        List<CommentDto> result = comments.stream().map(CommentDto::toDto).collect(Collectors.toList());

        return new pagingResponseDto().toDto(result, comments);
    }

    // 페이징처리를 통하여 회원이 작성한 게시글들을 확인
    public Page<Board> getBoardPage(String writer, int page, Pageable pageable) {
        pageable.withPage(page);
        Page<Board> pages = boardRepository.findAllByWriter(writer, pageable);
        if(pages.isEmpty()) throw new BoardNotFoundException();

        return pages;
    }

    // 페이징처리를 통하여 회원이 작성한 댓글들을 확인
    public Page<Comment> getCommentPage(String writer, int page, Pageable pageable) {
        pageable.withPage(page);
        Page<Comment> pages = commentRepository.findAllByWriter(writer, pageable);
        if(pages.isEmpty()) throw new CommentNotFoundException();

        return pages;
    }

    // 회원을 삭제하는 로직은 민감한 로직이기 때문에 1차적인 안전장치로 비밀번호를 통한 인증 절차 도입
    // 회원가입시에 입력하였던 비밀번호를 입력하여 회원 인증 수행
    public void validateUser(User user, UserDeleteDto deleteDto) {
        String password = deleteDto.getPassword();
        if(!password.equals(user.getPassword())) throw new UserInfoException();
    }

    // 회원 아이디나 이메일은 중복되지 않는 유일한 값이기 때문에
    // 회원 가입을 할 경우, 이미 존재하는 아이디나 이메일을 입력하였는지 확인하는 로직
    public void validateUser(UserCreateDto createDto) {
        String username = createDto.getUsername();
        String email = createDto.getEmail();

        if(userRepository.existsUserByUsernameOrEmail(username, email))
            throw new UserDuplicateException();
    }
}
