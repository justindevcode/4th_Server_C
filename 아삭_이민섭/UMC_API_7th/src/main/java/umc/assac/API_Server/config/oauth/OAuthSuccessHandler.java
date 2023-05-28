package umc.assac.API_Server.config.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import umc.assac.API_Server.config.jwt.TokenProvider;
import umc.assac.API_Server.dto.token.TokenDto;
import umc.assac.API_Server.service.redis.RedisService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@Transactional
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final RedisService redisService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        TokenDto tokenDto = makeToken(authentication);

        var writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(tokenDto));
        writer.flush();
    }

    public TokenDto makeToken(Authentication authentication) {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();

        UsernamePasswordAuthenticationToken token = getToken(principal);
        Authentication auth = authenticationManagerBuilder.getObject().authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);

        TokenDto tokenValue = tokenProvider.createToken(auth);
        redisService.setValue(auth.getName(), tokenValue.getRefreshToken());

        return tokenValue;
    }

    public UsernamePasswordAuthenticationToken getToken(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String username = (String)attributes.get("name");
        String provider = (String)attributes.get("provider");
        String email = (String)attributes.get("email");

        return new UsernamePasswordAuthenticationToken(provider + "-" + username, email);
    }
}
