package umc.assac.API_Server.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 올바른 JWT인지 확인하는 필터
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    // 요청 헤더값 내부에 존재하는 토큰값을 확인한 결과, 올바른 토큰이었다면
    // 이를 Authentication 객체로 만들어서 저장.
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filter) throws ServletException, IOException {
        String token = getToken(request);

        if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filter.doFilter(request, response);
    }

    // 헤더값 내부에 존재하는 JWT를 가져와, 유효한 토큰인지 확인하는 로직.
    // 유효하지 않은 토큰인 경우 null값 반환
    public String getToken(HttpServletRequest request) {
        String tokenValue = request.getHeader("Authorization");

        if(StringUtils.hasText(tokenValue) && tokenValue.startsWith("Bearer "))
            return tokenValue.substring(7);

        return null;
    }
}
