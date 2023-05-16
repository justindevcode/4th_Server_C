package umc.assac.API_Server.controller.board;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import umc.assac.API_Server.domain.user.User;
import umc.assac.API_Server.dto.board.BoardCreateDto;
import umc.assac.API_Server.dto.board.BoardEditDto;
import umc.assac.API_Server.exception.user.UserNotFoundException;
import umc.assac.API_Server.repository.user.UserRepository;
import umc.assac.API_Server.response.Response;
import umc.assac.API_Server.service.board.BoardService;

import javax.validation.Valid;

// 게시글과 관련된 요청들을 받아서 처리하는 Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;
    private final UserRepository userRepository;

    // 특정 카테고리에 해당하는 게시글들을 모두 조회하는 로직
    @GetMapping("/boards/all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "특정 카테고리 게시글 조회", notes = "특정 카테고리에 해당하는 모든 게시글들을 조회하는 로직")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "특정 카테고리를 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "page", value = "페이징 처리를 위한 페이지값")
    })
    public Response findAllBoards(
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @PageableDefault(size = 5, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return Response.success(boardService.findBoards(categoryId, page, pageable));
    }

    // 파라미터를 통하여 특정 게시글에 작성된 댓글들을 모두 조회하는 로직
    @GetMapping("/boards/all/comment")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "댓글 조회", notes = "특정 게시글에 작성된 모든 댓글들을 조회하는 로직")
    @ApiImplicitParam(name = "boardId", value = "특정 게시글을 식별하기 위한 PK값")
    public Response findComments(@RequestParam("boardId") Long boardId) {
        return Response.success(boardService.getComments(boardId));
    }

    // 게시글의 PK를 통하여 게시글을 단일 조회하는 로직
    @GetMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "게시글 단일 조회", notes = "특정 게시글을 단일 조회하는 로직")
    @ApiImplicitParam(name = "id", value = "특정 게시글을 식별하기 위한 PK값")
    public Response findBoard(@PathVariable Long id) {
        return Response.success(boardService.findBoard(id));
    }

    // 게시글을 생성하는 로직
    @PostMapping("/boards")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "게시글 생성", notes = "게시글을 생성하는 로직")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "특정 카테고리를 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "userId", value = "특정 사용자를 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "createDto", value = "게시글 생성에 필요한 데이터를 전달하는 Dto")
    })
    public void makeBoard(@RequestParam("categoryId") Long categoryId,
                          @RequestParam("userId") Long userId,
                          @RequestBody @Valid BoardCreateDto createDto) {
        User user = getUser(userId);
        boardService.makeBoard(createDto, categoryId, user);
    }

    // 게시글의 PK를 통하여 게시글을 수정하는 로직
    @PutMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "게시글 수정", notes = "특정 게시글을 수정하는 로직")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "특정 사용자를 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "id", value = "특정 게시글을 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "editDto", value = "게시글 수정에 필요한 데이터를 전달하는 Dto")
    })
    public void editBoard(@RequestParam("userId") Long userId,
                          @PathVariable Long id,
                          @RequestBody @Valid BoardEditDto editDto) {
        User user = getUser(userId);
        boardService.editBoard(editDto, id, user);
    }

    // 게시글의 PK를 통하여 게시글을 삭제하는 로직
    @DeleteMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "게시글 삭제", notes = "특정 게시글을 삭제하는 로직")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "특정 사용자를 식별하기 위한 PK값"),
            @ApiImplicitParam(name = "id", value = "특정 게시글을 식별하기 위한 PK값")
    })
    public void deleteBoard(@RequestParam("userId") Long userId,
                            @PathVariable Long id) {
        User user = getUser(userId);
        boardService.deleteBoard(id, user);
    }

    // 게시글을 예약하는 로직
    @PutMapping("/boards")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "게시글 예약", notes = "특정 게시글을 예약하는 로직")
    @ApiImplicitParam(name = "boardId", value = "특정 게시글을 식별하기 위한 PK값")
    public void reserveBoard(@RequestParam("boardId") Long boardId) {
        boardService.reserveBoard(boardId);
    }

    // User Entity의 PK값을 통하여 사용자의 정보를 가져오는 로직
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }
}
