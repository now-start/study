package org.nowstart.study.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.nowstart.study.domain.dto.UserDto;
import org.nowstart.study.domain.mapper.UserMapper;
import org.nowstart.study.repository.UserRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService service;
    @Mock
    UserRepository repository;
    @Spy
    UserMapper mapper;

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