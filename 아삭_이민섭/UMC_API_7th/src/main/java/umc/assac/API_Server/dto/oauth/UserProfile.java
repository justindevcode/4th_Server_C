package umc.assac.API_Server.dto.oauth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

// 사용자의 정보를 저장하는 일종의 DTO 파일
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile {
    private String username; // 소셜 로그인 사용자의 이름
    private String provider; // 서비스(ex) 구글, 카카오)
    private String email; // 소셜 로그인 사용자의 이메일

    @Builder
    public static UserProfile getInstance(String username, String provider, String email) {
        return new UserProfile(username, provider, email);
    }
}
