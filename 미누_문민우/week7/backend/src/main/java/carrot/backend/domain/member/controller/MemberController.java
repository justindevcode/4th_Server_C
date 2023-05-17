package carrot.backend.domain.member.controller;

import carrot.backend.domain.member.dto.EditMemberInfoRequestDto;
import carrot.backend.domain.member.service.MemberService;
import carrot.backend.response.Response;
import carrot.backend.response.SuccessMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static carrot.backend.response.Response.*;
import static carrot.backend.response.SuccessMessage.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(OK)
    @GetMapping()
    public Response getMemberInfo(Long memberId) {
        return success(SUCCESS_TO_GET_MEMBER, memberService.getMemberInfo(memberId));
    }

    @ResponseStatus(OK)
    @PatchMapping()
    public Response editMemberInfo(@RequestBody EditMemberInfoRequestDto editMemberInfoRequestDto, Long memberId) {
        memberService.editMemberInfo(editMemberInfoRequestDto, memberId);
        return success(SUCCESS_TO_EDIT_MEMBER);
    }

    @ResponseStatus(OK)
    @DeleteMapping()
    public Response deleteMember(Long memberId) {
        memberService.deleteMember(memberId);
        return success(SUCCESS_TO_DELETE_MEMBER);
    }
}
