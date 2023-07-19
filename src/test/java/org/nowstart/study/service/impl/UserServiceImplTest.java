package org.nowstart.study.service.impl;

import com.sun.jdi.request.DuplicateRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.nowstart.study.data.dto.user.UserSaveDto;
import org.nowstart.study.data.entity.UserEntity;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl service;
    @Mock
    UserRepository repository;
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