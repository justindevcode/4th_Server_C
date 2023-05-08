package carrot.backend.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMemberInfoRequestDto {

    private String nickname;
    private String phone;
    private String location;
}
