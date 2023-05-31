package org.nowstart.study.presentation.config;


import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final SecurityService service;
    private final JwtFilter filter;
    private final JwtProvider provide;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable()
            .cors().disable()
            .headers().frameOptions().sameOrigin().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests().anyRequest().authenticated().and()
            .formLogin()
            .successHandler((request, response, auth) -> {
                String userId = auth.getName();
                log.info("[SpringSecurityConfig][filterChain][success][{}]", userId);
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                response.setHeader("Content-Type", "application/download; UTF-8");
                String token = provide.generateToken(userId, auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
                response.getWriter().write("{\"result\" : \"" + token + "\" }");
            }).failureHandler((request, response, auth) -> {  //로그인 실패시 행동을 정의 합니다.
                String ip = request.getRemoteAddr();
                String userId = request.getParameter("id");
                log.info("[SpringSecurityConfig][filterChain][fail][{}][{}]", ip, userId);
                response.sendRedirect("/");
            }).permitAll().and()
            .build();
    }
}