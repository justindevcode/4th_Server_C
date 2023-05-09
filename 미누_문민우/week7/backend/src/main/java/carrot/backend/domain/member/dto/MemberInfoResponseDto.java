package carrot.backend.domain.member.dto;

import carrot.backend.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoResponseDto {

    private Long id;
    private String nickname;
    private String phone;
    private String location;

    public static MemberInfoResponseDto toDto(Member member) {
        return MemberInfoResponseDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .phone(member.getPhone())
                .location(member.getLocation())
                .build();
    }
}
