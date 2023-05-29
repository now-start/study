package org.nowstart.study.service;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.domain.dto.UserDto;
import org.nowstart.study.domain.mapper.UserMapper;
import org.nowstart.study.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;


    public void saveUser(UserDto userDto) {
        userRepository.findById(userDto.getId()).ifPresent(e -> {
            throw new DuplicateRequestException();
        });
        userRepository.save(userMapper.toEntity(userDto));
    }
}
