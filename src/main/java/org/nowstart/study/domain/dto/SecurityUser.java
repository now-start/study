package org.nowstart.study.domain.dto;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
            new SimpleGrantedAuthority("auth1"),
            new SimpleGrantedAuthority("auth2")
        );
    }

    @Override
    public String getUsername() {
        return "admin";
    }

    @Override
    public String getPassword() {
        return "$2a$10$SDBxd18/9SovlON7h/HewOwTe/drGLIx/UV0G0k91qLRWnGz0VoR.";  //1234
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}