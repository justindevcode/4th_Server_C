package umc.test.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.test.board.dto.user.UserCreateRequestDto;
import umc.test.board.dto.user.UserListResponseDto;
import umc.test.board.dto.user.UserResponseDto;
import umc.test.board.dto.user.UserUpdateRequestDto;
import umc.test.board.service.UserService;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    //private final UserAuthService userAuthService;




    // 사용자 등록
    @PostMapping("/user")
    public Long create(@RequestBody UserCreateRequestDto requestDto){
        return userService.save(requestDto);
    }

    // 사용자 정보 변경
    @PutMapping("/user/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto){
        return userService.update(id,requestDto);
    }

    // 사용자 조회
    @GetMapping("/user/{id}")
    public UserResponseDto searchById(@PathVariable Long id) {
        return userService.searchById(id);
    }
    // 사용자 전체 조회
    @GetMapping("/user")
    public List<UserListResponseDto> searchAlldesc(){
        return userService.searchAllDesc();
    }

    // 사용자 정보 삭제
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }



}
