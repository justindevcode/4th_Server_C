package umc.assac.API_Server.controller.comment;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import umc.assac.API_Server.domain.user.User;
import umc.assac.API_Server.dto.comment.CommentRequestDto;
import umc.assac.API_Server.exception.user.UserNotFoundException;
import umc.assac.API_Server.repository.user.UserRepository;
import umc.assac.API_Server.service.comment.CommentService;

import javax.validation.Valid;

// 댓글과 관련된 로직들을 관리하는 CommentController
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;
    private final UserRepository userRepository;

    // 댓글을 생성하는 로직
    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "댓글 생성", notes = "댓글을 새롭게 생성하는 로직")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardId", value = "게시글을 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "requestDto", value = "댓글을 생성하기 위하여 필요한 정보가 담긴 DTO")
    })
    public void makeComment(@RequestParam("boardId") Long boardId,
                            @RequestBody @Valid CommentRequestDto requestDto) {
        Authentication authentication = getAuthentication();
        User user = getUser(authentication);

        commentService.makeComment(requestDto, boardId, user);
    }

    // 댓글을 수정하는 로직
    @PutMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "댓글 수정", notes = "특정 댓글을 수정하기 위한 로직")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "댓글을 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "requestDto", value = "댓글을 수정하기 위하여 필요한 정보가 담긴 DTO")
    })
    public void editComment(@RequestBody @Valid CommentRequestDto requestDto,
                            @PathVariable Long id) {
        Authentication authentication = getAuthentication();
        User user = getUser(authentication);
        commentService.editComment(id, requestDto, user);
    }

    // 댓글을 삭제하는 로직
    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제하기 위한 로직")
    @ApiImplicitParam(name = "id", value = "댓글을 식별하기 위한 PK값")
    public void deleteComment(@PathVariable Long id) {
        Authentication authentication = getAuthentication();
        User user = getUser(authentication);
        commentService.deleteComment(id, user);
    }

    // Authentication 객체를 저장하고 있는 SecurityContextHolder로부터 Authentication 객체를 가져오는 로직
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    // JWT 토큰 내부에 담긴 내용을 통하여 사용자 정보를 가져오는 로직
    public User getUser(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
    }
}
