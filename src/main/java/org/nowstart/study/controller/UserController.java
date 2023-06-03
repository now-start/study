package org.nowstart.study.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.dto.UserDto;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.data.vo.request.UserRequestVo;
import org.nowstart.study.data.vo.response.CommResponseVo;
import org.nowstart.study.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final Mapper mapper;
    private final UserService userService;

    @PostMapping("/user")
    public CommResponseVo<UserDto> saveUser(@RequestBody @Valid UserRequestVo userRequestVo) {
        log.info("[UserController][saveUser][/user]");
        userService.saveUser(mapper.toDto(userRequestVo));
        return CommResponseVo.<UserDto>builder().build();
    }

}
