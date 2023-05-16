package umc.assac.API_Server.controller.user;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umc.assac.API_Server.domain.user.User;
import umc.assac.API_Server.dto.user.UserCreateDto;
import umc.assac.API_Server.dto.user.UserDeleteDto;
import umc.assac.API_Server.exception.user.UserNotFoundException;
import umc.assac.API_Server.repository.user.UserRepository;
import umc.assac.API_Server.response.Response;
import umc.assac.API_Server.service.user.UserService;

import javax.validation.Valid;

// 회원과 관련된 로직들을 처리하는 UserController
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    // 새로운 회원을 등록하는 로직
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "신규 회원 등록", notes = "새로운 회원을 등록하는 로직")
    @ApiImplicitParam(name = "createDto", value="회원 가입에 필요한 정보를 저장하기 위한 DTO")
    public void saveUser(@RequestBody @Valid UserCreateDto createDto) {
        userService.saveUser(createDto);
    }

    // 회원의 정보를 조회하는 로직
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "회원 정보 조회", notes = "회원의 상세한 정보를 조회하는 로직")
    @ApiImplicitParam(name = "id", value = "회원을 조회하기 위한 User Entity의 PK값")
    public Response getUserInfo(@PathVariable Long id) {
        User user = validateUser(id);
        return Response.success(userService.getUserInfo(user));
    }

    // 사용자가 작성한 댓글들을 조회하는 로직
    @GetMapping("/users/{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "사용자 작성 댓글 조회", notes = "사용자가 작성한 댓글들을 확인하는 로직")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "회원을 조회하기 위한 User Entity의 PK값"),
            @ApiImplicitParam(name = "page", value = "페이징 처리를 위한 page값"),
    })
    public Response getUserComments(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @PageableDefault(size = 5, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        User user = validateUser(id);
        return Response.success(userService.getUserComments(user, page, pageable));
    }

    // 사용자가 작성한 게시글들을 조회하는 로직
    @GetMapping("/users/{id}/boards")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "사용자 작성 게시글 조회", notes = "사용자가 작성한 게시글들을 확인하는 로직")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "회원을 조회하기 위한 User Entity의 PK값"),
            @ApiImplicitParam(name = "page", value = "페이징 처리를 위한 page값")
    })
    public Response getUserBoards(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @PageableDefault(size = 5, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        User user = validateUser(id);
        return Response.success(userService.getUserBoards(user, page, pageable));
    }

    // 사용자의 회원 정보를 삭제하는 로직
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "회원 삭제 로직", notes = "사용자의 정보를 삭제하는 로직")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "회원을 조회하기 위한 User Entity의 PK값"),
            @ApiImplicitParam(name = "deleteDto", value = "회원을 삭제하기 위하여 필요한 정보를 담고 있는 DTO")
    })
    public void deleteUser(@PathVariable Long id, @RequestBody @Valid UserDeleteDto deleteDto) {
        User user = validateUser(id);
        userService.deleteUser(user, deleteDto);
    }

    //특정 사용자를 신고하는 로직
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "사용자 신고 로직", notes = "특정 사용자를 신고하는 로직")
    @ApiImplicitParam(name = "id", value = "회원을 조회하기 위한 User Entity의 PK값")
    public void reportUser(@PathVariable Long id) {
        User user = validateUser(id);
        userService.reportUser(user);
    }

    // userId를 통하여 일치하는 사용자가 있는지 확인하고, 일치하지 않는 경우에는 오류를 반환하는 메서드
    private User validateUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
