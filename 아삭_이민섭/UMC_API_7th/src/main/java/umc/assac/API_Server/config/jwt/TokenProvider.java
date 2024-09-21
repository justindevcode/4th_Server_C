package umc.assac.API_Server.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import umc.assac.API_Server.dto.token.TokenDto;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// JWT 토큰과 관련된 로직을 수행하는 파일
// 토큰의 생성, 유효성 확인, 토큰을 통한 Authentication 객체 생성의 역할을 수행
@Component
@Slf4j
public class TokenProvider implements InitializingBean {

    private final String AUTHORIZATION_KEY = "auth";
    private final Long validationTime; // 토큰의 유효 시간
    private final Long refreshValidationTime; // 재발근된 토큰의 유효 기간
    private final String secretKey; // 토큰을 암호화하기 위하여 필요한 키 값

    private Key key; // JWT 토큰을 직접적으로 암호화하기 위하여 사용하는 키

    public TokenProvider(@Value("${jwt.secretKey}") String secretKey,
            @Value("${jwt.validationTime}") Long validationTime) {
        this.validationTime = validationTime * 1000; // 단위가 ms이므로, 1000을 곱하여 초로 바꿔줍니다.
        this.refreshValidationTime = validationTime * 2 * 1000; // 재발급 토큰의 경우, 기존의 유효 기간보다 길게 설정해줍니다.
        this.secretKey = secretKey;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keySet = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keySet);
    }

    // Authentication 객체를 바탕으로 JWT 토큰을 생성하는 로직
    public TokenDto createToken(Authentication authentication) {
        // Authentication 객체가 가지고 있던 사용자의 권한들을 파싱
        String authorities = authentication.getAuthorities().stream().
                map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        long now = new Date().getTime(); // 토큰의 유효 기간을 설정하기 위하여 현재 시간을 가져옴

        Date expiration = new Date(now + validationTime); // 기본 토큰의 유효 기간
        Date refreshExpiration = new Date(now + refreshValidationTime); // 재발급 토큰의 유효 기간

        String accessToken = Jwts.builder()
                .setExpiration(expiration)
                .setSubject(authentication.getName())
                .claim(AUTHORIZATION_KEY, authorities)
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact(); // 접근된 페이지에 접근할 수 있는 accessToken 생성

        String refreshToken = Jwts.builder()
                .setExpiration(refreshExpiration)
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact(); // 토큰의 유효기간이 만료되었을 경우, 새로운 토큰을 발급 받을 수 있는 refreshToken 생성

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .validationTime(expiration.getTime())
                .type("Bearer").build();
    }

    // JWT 토큰을 바탕으로 Authentication 객체를 생성하는 로직
    public Authentication getAuthentication(String token) {
        // 토큰 내부에 위치한 데이터를 가져옴
        Claims claims = parseDate(token);

        // 토큰 내부 데이터로부터 사용자가 가지고 있는 권한 정보들을 가져옴
        List<SimpleGrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORIZATION_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new).toList();

        User user = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(user, "", authorities);
    }

    public Claims parseDate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build().parseClaimsJws(token)
                .getBody();
    }

    // 해당 토큰이 유효한 토큰인지 확인하는 로직
    public boolean validateToken(String token) {
        try {
            parseDate(token);
            return true;
        } catch(ExpiredJwtException e) {
            log.info("유효 기간이 만료된 토큰입니다.");
        } catch(MalformedJwtException | SecurityException e) {
            log.info("잘못된 형식의 토큰입니다.");
        } catch(UnsupportedJwtException e) {
            log.info("지원하지 않는 토큰입니다.");
        } catch(IllegalArgumentException e) {
            log.info("잘못된 토큰입니다.");
        }
        return false;
    }
}
