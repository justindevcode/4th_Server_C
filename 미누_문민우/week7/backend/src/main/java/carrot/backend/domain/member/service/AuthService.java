package carrot.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import carrot.backend.config.jwt.JwtProvider;
import carrot.backend.config.redis.RedisService;
import carrot.backend.domain.member.dto.sign.*;
import carrot.backend.domain.member.entity.Member;
import carrot.backend.domain.member.repository.MemberRepository;
import carrot.backend.exception.situation.UsernameAlreadyExistsException;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisService redisService;
    private final JwtProvider jwtProvider;

    public void signUp(SignUpRequestDto req) {
        validateSignUpInfo(req);
        memberRepository.save(req.toEntity(passwordEncoder));
    }

    public TokenResponseDto signIn(LoginRequestDto req) {
        TokenDto tokenDto = authorize(authenticationManagerBuilder.getObject().authenticate(req.toAuthentication()));
        return new TokenResponseDto(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
    }

    public TokenResponseDto reissue(TokenRequestDto tokenRequestDto) {
        validateRefreshToken(tokenRequestDto);
        Authentication authentication = jwtProvider.getAuthentication(tokenRequestDto.getAccessToken());
        validateRefreshTokenOwner(authentication, tokenRequestDto);

        TokenDto tokenDto = authorize(authentication);
        return new TokenResponseDto(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
    }

    private void validateSignUpInfo(SignUpRequestDto req) {
        if(memberRepository.existsByUsername(req.getUsername())) {
            throw new UsernameAlreadyExistsException(req.getUsername());
        }
    }

    private TokenDto authorize(Authentication authentication) {
        TokenDto tokenDto = jwtProvider.generateTokenDto(authentication);
        redisService.setValues("RT: " + authentication.getName(), tokenDto.getRefreshToken(), Duration.ofMillis(tokenDto.getRefreshTokenExpiresIn()));
        return tokenDto;
    }

    private void validateRefreshToken(TokenRequestDto tokenRequestDto) {
        if (!jwtProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }
    }

    private void validateRefreshTokenOwner(Authentication authentication, TokenRequestDto tokenRequestDto) {
        if (!redisService.getValues("RT: " + authentication.getName()).equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }
    }
}
