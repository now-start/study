package org.nowstart.study.presentation.config;

import jakarta.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    public static final String AUTHORIZATION = "Authorization";
    private static final long TIME = 24 * 60 * 60 * 1000L;
    private String jwtKey = "myJWTKey";

    private final UserDetailsService user;

    @PostConstruct
    protected void passwordEncoder() {
        jwtKey = Base64.getEncoder().encodeToString(jwtKey.getBytes());
    }

    // JWT 토큰 생성
    public String generateToken(String userPk, List<String> roles) {
        Date now = new Date();
        return Jwts.builder()
            .setSubject(userPk)
            .claim("roles", roles)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + TIME))
            .signWith(SignatureAlgorithm.HS256, jwtKey)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = user.loadUserByUsername(this.parseClaims(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(jwtToken);
            System.out.println(claims);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String parseClaims(String token) {
        return Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody().getSubject();
    }
}