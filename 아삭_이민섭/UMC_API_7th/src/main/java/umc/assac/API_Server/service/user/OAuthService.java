package umc.assac.API_Server.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import umc.assac.API_Server.domain.user.User;
import umc.assac.API_Server.dto.oauth.OAuthAttributes;
import umc.assac.API_Server.dto.oauth.UserProfile;
import umc.assac.API_Server.repository.user.UserRepository;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// OAuth를 통하여 소셜 로그인에 성공하였을 경우, 해당 서비스 로직을 수행하게 됨
@Service
@RequiredArgsConstructor
public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        Map<String, Object> attributes = oAuth2User.getAttributes();

        UserProfile userProfile = OAuthAttributes.extract(registrationId, attributes);

        saveUser(userProfile);

        Map<String, Object> customAttribute =
                getCustomAttribute(registrationId, userNameAttributeName, attributes, userProfile);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                customAttribute,
                userNameAttributeName);
    }

    public Map getCustomAttribute(String registrationId,
                                  String userNameAttributeName,
                                  Map<String, Object> attributes,
                                  UserProfile userProfile) {
        Map<String, Object> customAttribute = new ConcurrentHashMap<>();

        customAttribute.put(userNameAttributeName, attributes.get(userNameAttributeName));
        customAttribute.put("provider", registrationId);
        customAttribute.put("name", userProfile.getUsername());
        customAttribute.put("email", userProfile.getEmail());

        return customAttribute;
    }

    // 소셜 로그인한 사용자의 정보를 통하여 회원 가입 수행
    public void saveUser(UserProfile userProfile) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String email = userProfile.getEmail();

        if(!userRepository.existsUserByEmail(email)) {
            String username = userProfile.getProvider() + "-" + userProfile.getUsername();
            String password = passwordEncoder.encode(email);

            User user = User.getUser(userProfile, username, password);

            userRepository.save(user);
        }
    }
}
