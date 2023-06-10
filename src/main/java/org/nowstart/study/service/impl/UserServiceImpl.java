package org.nowstart.study.service.impl;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.dto.UserDto;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.repository.UserRepositoryBoard;
import org.nowstart.study.service.UserService;
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
public class UserServiceImpl implements UserService, UserDetailsService {

    private final Mapper mapper;
    private final UserRepositoryBoard userRepository;
    private final PasswordEncoder passwordEncoder;


    public void saveUser(UserDto userDto) {
        userRepository.findById(userDto.getId()).ifPresent(e -> {
            throw new DuplicateRequestException();
        });
        userRepository.save(mapper.toEntity(userDto, passwordEncoder.encode(userDto.getPassword())));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없음"));
    }
}
