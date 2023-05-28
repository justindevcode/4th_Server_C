package umc.assac.API_Server.controller.user;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import umc.assac.API_Server.domain.user.User;
import umc.assac.API_Server.dto.token.TokenRequestDto;
import umc.assac.API_Server.dto.user.UserCreateDto;
import umc.assac.API_Server.dto.user.UserLoginDto;
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
    @PostMapping("/users/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "신규 회원 등록", notes = "새로운 회원을 등록하는 로직")
    @ApiImplicitParam(name = "createDto", value="회원 가입에 필요한 정보를 저장하기 위한 DTO")
    public void saveUser(@RequestBody @Valid UserCreateDto createDto) {
        userService.saveUser(createDto);
    }

    // 로그인을 수행하는 로직
    @PostMapping("/users/sign-in")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "회원 로그인", notes = "사용자의 아이디와 비밀번호를 입력하여 로그인하는 로직")
    @ApiImplicitParam(name = "loginDto", value = "로그인에 필요한 정보들이 담겨져 있는 DTO")
    public Response logInUser(@RequestBody @Valid UserLoginDto loginDto) {
        return Response.success(userService.loginUser(loginDto));
    }

    // 토큰을 재발급받는 로직
    @PostMapping("/users/reIssue")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "토큰 재발급", notes = "만료 기간이 다 된 토큰을 재발급하는 로직")
    public Response reIssue(@RequestBody @Valid TokenRequestDto requestDto) {
        return Response.success(userService.reIssue(requestDto));
    }

    // 회원의 정보를 조회하는 로직
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "회원 정보 조회", notes = "회원의 상세한 정보를 조회하는 로직")
    public Response getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = validateUser(authentication);

        return Response.success(userService.getUserInfo(user));
    }

    // 사용자가 작성한 댓글들을 조회하는 로직
    @GetMapping("/users/comments")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "사용자 작성 댓글 조회", notes = "사용자가 작성한 댓글들을 확인하는 로직")
    @ApiImplicitParam(name = "page", value = "페이징 처리를 위한 page값")
    public Response getUserComments(
            @RequestParam(defaultValue = "0") int page,
            @PageableDefault(size = 5, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = validateUser(authentication);
        return Response.success(userService.getUserComments(user, page, pageable));
    }

    // 사용자가 작성한 게시글들을 조회하는 로직
    @GetMapping("/users/boards")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "사용자 작성 게시글 조회", notes = "사용자가 작성한 게시글들을 확인하는 로직")
    @ApiImplicitParam(name = "page", value = "페이징 처리를 위한 page값")
    public Response getUserBoards(
            @RequestParam(defaultValue = "0") int page,
            @PageableDefault(size = 5, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = validateUser(authentication);
        return Response.success(userService.getUserBoards(user, page, pageable));
    }

    // 사용자의 회원 정보를 삭제하는 로직
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "회원 삭제 로직", notes = "사용자의 정보를 삭제하는 로직")
    public void deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = validateUser(authentication);
        userService.deleteUser(user);
    }

    //특정 사용자를 신고하는 로직
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "사용자 신고 로직", notes = "특정 사용자를 신고하는 로직")
    @ApiImplicitParam(name = "id", value = "회원을 조회하기 위한 User Entity의 PK값")
    public void reportUser(@PathVariable Long id) {
        userService.reportUser(id);
    }

    // 현재 사용자의 정보를 가져와, 해당 사용자가 유효한 사용자인지 아닌지를 판단
    private User validateUser(Authentication authentication) {
        String username = authentication.getName();

        return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
    }
}
