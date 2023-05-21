package carrot.backend.domain.member.service;

import carrot.backend.domain.member.dto.CreateMemberRequestDto;
import carrot.backend.domain.member.dto.EditMemberInfoRequestDto;
import carrot.backend.domain.member.dto.MemberInfoResponseDto;
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

    public void createMember(CreateMemberRequestDto createMemberRequestDto) {
        memberRepository.save(createMemberRequestDto.toEntity());
    }

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
