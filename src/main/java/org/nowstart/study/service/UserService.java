package org.nowstart.study.service;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.dto.UserDto;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final Mapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void saveUser(UserDto userDto) {
        userRepository.findById(userDto.getId()).ifPresent(e -> {
            throw new DuplicateRequestException();
        });
        userRepository.save(mapper.toEntity(userDto, passwordEncoder.encode(userDto.getPassword())));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findById(userName).map(user -> User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(user.getRoles().toArray(String[]::new))
                .build())
            .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없음"));
    }
}
