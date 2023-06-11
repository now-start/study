package org.nowstart.study.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.sun.jdi.request.DuplicateRequestException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.nowstart.study.data.dto.user.UserSaveDto;
import org.nowstart.study.data.entity.UserEntity;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.repository.UserRepositoryBoard;
import org.nowstart.study.service.impl.UserServiceImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @InjectMocks
    UserServiceImpl service;
    @Mock
    UserRepositoryBoard repository;
    @Spy
    Mapper mapper;
    @Spy
    PasswordEncoder passwordEncoder;

    @Test
    void saveUser() {
        //given
        UserSaveDto userSaveDto = UserSaveDto.builder()
            .id("id")
            .password("password")
            .name("name")
            .build();

        //when

        //then
        assertDoesNotThrow(() -> service.saveUser(userSaveDto));
    }

    @Test
    void saveUser_exception() {
        //given
        UserSaveDto userSaveDto = UserSaveDto.builder()
            .id("id")
            .password("password")
            .name("name")
            .build();
        given(repository.findById(any())).willReturn(Optional.of(UserEntity.builder().build()));

        //when

        //then
        assertThatThrownBy(() -> service.saveUser(userSaveDto)).isInstanceOf(DuplicateRequestException.class);
    }

    @Test
    void loadUserByUsername() {
        //given
        UserSaveDto userSaveDto = UserSaveDto.builder()
            .id("id")
            .password("password")
            .name("name")
            .build();
        given(repository.findById(any())).willReturn(Optional.of(UserEntity.builder().build()));

        //when

        //then
        assertDoesNotThrow(() -> service.loadUserByUsername(userSaveDto.getId()));
    }

    @Test
    void loadUserByUsername_exception() {
        //given
        UserSaveDto userSaveDto = UserSaveDto.builder()
            .id("id")
            .password("password")
            .name("name")
            .build();
        given(repository.findById(any())).willReturn(Optional.empty());

        //when

        //then
        assertThatThrownBy(() -> service.loadUserByUsername(userSaveDto.getId())).isInstanceOf(UsernameNotFoundException.class);
    }
}