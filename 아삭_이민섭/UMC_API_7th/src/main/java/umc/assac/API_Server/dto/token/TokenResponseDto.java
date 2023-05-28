package umc.assac.API_Server.dto.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;

    public static TokenResponseDto toDto(TokenDto tokenDto) {
        return new TokenResponseDto(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
    }
}
