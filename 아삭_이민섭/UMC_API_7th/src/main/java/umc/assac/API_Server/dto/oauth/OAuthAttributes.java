package umc.assac.API_Server.dto.oauth;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

// 어떠한 서비스를 사용하느냐에 따라서 정보를 파싱하는 방식이 다름
// 소셜 로그인을 통하여 반환되는 정보들을 파싱하여 사용자와 관련된 정보만을 반환하는 역할 수행
public enum OAuthAttributes {

    GOOGLE("google", (attribute) -> {
        String email = (String) attribute.get("email");
        String username = (String) attribute.get("name");

        return UserProfile.getInstance(username, "google", email);
    }),
    KAKAO("kakao", (attribute) -> {
        Map<String, Object> usernameSet = (Map) attribute.get("properties");
        Map<String, Object> emailSet = (Map) attribute.get("kakao_account");

        String username = (String)usernameSet.get("nickname");
        String email = (String)emailSet.get("email");

        return UserProfile.getInstance(username, "kakao", email);
    });

    private final String registrationId; // OAuth를 활용한 로그인을 지원하는 서비스
    private final Function<Map<String, Object>, UserProfile> attributes; // 사용자 정보를 저장하는 객체

    OAuthAttributes(String registrationId, Function<Map<String, Object>, UserProfile> attributes) {
        this.registrationId = registrationId;
        this.attributes = attributes;
    }

    public static UserProfile extract(String registrationId, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(value -> registrationId.equals(value.registrationId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .attributes.apply(attributes);
    }
}
