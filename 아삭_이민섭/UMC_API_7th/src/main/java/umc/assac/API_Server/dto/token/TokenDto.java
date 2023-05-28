package umc.assac.API_Server.dto.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 토큰과 관련된 정보를 저장하여 전달하기 위한 DTO 파일
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TokenDto {

    private String accessToken;
    private String refreshToken;
    private Long validationTime;
    private String type;
}
