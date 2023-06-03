package org.nowstart.study.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.type.RolesType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private static final Long EXPIRED_MS = 60 * 60 * 1000L;
    @Value("${spring.security.password:}")
    private String secretKey;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
            "/test/**"
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable()
            .cors().disable()
            .headers().frameOptions().sameOrigin().and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/user").hasRole(RolesType.ADMIN.getRole())
            .anyRequest().authenticated().and()
            .formLogin()
            .defaultSuccessUrl("/swagger-ui.html")
//            .successHandler((request, response, authentication) -> {
//                String username = request.getParameter("username");
//                String token = JwtUtil.createJwt(username, secretKey, EXPIRED_MS);
//                log.info("[SpringSecurityConfig][filterChain][success] : {} {}", username, token);
//                response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
//            })
            .permitAll().and()
            .build();
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authorization = request.getHeader("Authorization");
//        String token = StringUtils.hasText(authorization) && authorization.startsWith("Bearer") ? authorization.split(" ")[1] : null;
//
//        if (token != null && JwtUtil.validateToken(token, secretKey)) {
//            String userName = JwtUtil.getUserName(token, secretKey);
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, null);
//            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        }
//        filterChain.doFilter(request, response);
//    }
}