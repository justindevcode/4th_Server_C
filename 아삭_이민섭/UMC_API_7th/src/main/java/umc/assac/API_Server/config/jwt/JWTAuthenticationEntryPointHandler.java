package umc.assac.API_Server.config.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Authentication 객체는 가지고 있지만, 해당 객체가 가지고 있는 권한으로는 접근할 수 없는 곳으로 이동하려는 경우, 오류를 반환하도록 설정하는 파일
@Component
public class JWTAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }
}
