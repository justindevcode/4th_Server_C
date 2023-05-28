package umc.assac.API_Server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import umc.assac.API_Server.config.jwt.*;
import umc.assac.API_Server.config.oauth.OAuthSuccessHandler;
import umc.assac.API_Server.service.user.OAuthService;

// Spring Security와 JWT를 활용한 보안 설정을 담당하는 파일
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAccessDeniedHandler accessDeniedHandler;
    private final JWTAuthenticationEntryPointHandler authenticationEntryPointHandler;
    private final TokenProvider tokenProvider;
    private final OAuthService oAuthService;
    private final OAuthSuccessHandler oAuthSuccessHandler;

    private final String SWAGGER_PERMIT_ARRAY[] = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.formLogin().disable()
                .logout().disable()
                .csrf().disable()
                .headers().disable()

                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPointHandler)
                .accessDeniedHandler(accessDeniedHandler)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
//                .antMatchers("/api/users/sign-up", "/api/users/sign-in", "/api/users/reIssue").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers(SWAGGER_PERMIT_ARRAY).permitAll()
                .anyRequest().authenticated()

                .and()
                .oauth2Login()
                .successHandler(oAuthSuccessHandler)
                .userInfoEndpoint()
                .userService(oAuthService)
                .and().and()
                .apply(new JwtSecurityConfig(tokenProvider))
                .and().build();
    }
}
