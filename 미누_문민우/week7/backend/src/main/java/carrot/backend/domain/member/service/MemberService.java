package carrot.backend.domain.member.service;

import carrot.backend.domain.member.dto.member.EditMemberInfoRequestDto;
import carrot.backend.domain.member.dto.member.MemberInfoResponseDto;
import carrot.backend.domain.member.entity.Member;
import carrot.backend.domain.member.repository.MemberRepository;
import carrot.backend.exception.situation.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberInfoResponseDto getMemberInfo(Long memberId) {
        return MemberInfoResponseDto.toDto(findMember(memberId));
    }

    @Transactional
    public void editMemberInfo(EditMemberInfoRequestDto editMemberInfoRequestDto, Long memberId) {
        findMember(memberId).editMember(editMemberInfoRequestDto.getNickname(), editMemberInfoRequestDto.getPhone(),
                editMemberInfoRequestDto.getLocation());
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.delete(findMember(memberId));
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
    }
}
