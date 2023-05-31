package org.nowstart.study.service;

import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.domain.dto.SecurityUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("들어온 사용자 아이디 :: {}", username);
        SecurityUser user = new SecurityUser();
        UserDetails build = null;

        try {
            User.UserBuilder userBuilder = User.withUsername(username).password(user.getPassword());
            userBuilder.authorities(user.getAuthorities());
            build = userBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return build;
    }

}