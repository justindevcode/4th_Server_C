package umc.test.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import umc.test.board.domain.TokenInfo;
import umc.test.board.dto.member.MemberLoginRequestDto;
import umc.test.board.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

//    @GetMapping("/")
//    public String test(){
//        return "hello world";
//    }

    // 사용자 로그인
    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto requestDto){
        TokenInfo token = memberService.login(requestDto.getMemberId(), requestDto.getPassword());

        return token;
    }
}
