package org.nowstart.study.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.nowstart.study.data.dto.UserDto;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService service;
    @Mock
    UserRepository repository;
    @Spy
    Mapper mapper;
    @Spy
    PasswordEncoder passwordEncoder;

    @Test
    void saveUser() {
        //given
        UserDto userDto = UserDto.builder()
            .id("id")
            .password("password")
            .name("name")
            .build();

        //when

        //then
        assertDoesNotThrow(() -> service.saveUser(userDto));
    }
}