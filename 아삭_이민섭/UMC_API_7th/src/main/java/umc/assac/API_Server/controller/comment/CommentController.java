package umc.assac.API_Server.controller.comment;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            @ApiImplicitParam(name = "userId", value = "사용자를 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "boardId", value = "게시글을 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "requestDto", value = "댓글을 생성하기 위하여 필요한 정보가 담긴 DTO")
    })
    public void makeComment(@RequestParam("userId") Long userId,
                                @RequestParam("boardId") Long boardId,
                                @RequestBody @Valid CommentRequestDto requestDto) {
        User user = getUser(userId);
        commentService.makeComment(requestDto, boardId, user);
    }

    // 댓글을 수정하는 로직
    @PutMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "댓글 수정", notes = "특정 댓글을 수정하기 위한 로직")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자를 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "id", value = "댓글을 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "requestDto", value = "댓글을 수정하기 위하여 필요한 정보가 담긴 DTO")
    })
    public void editComment(@RequestParam("userId") Long userId,
                            @RequestBody @Valid CommentRequestDto requestDto,
                            @PathVariable Long id) {
        User user = getUser(userId);
        commentService.editComment(id, requestDto, user);
    }

    // 댓글을 삭제하는 로직
    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제하기 위한 로직")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자를 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "id", value = "댓글을 식별하기 위한 PK값")
    })
    public void deleteComment(@RequestParam("userId") Long userId,
                              @PathVariable Long id) {
        User user = getUser(userId);
        commentService.deleteComment(id, user);
    }

    // 사용자 정보를 받아오는 로직
    public User getUser(Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return findUser;
    }
}
