package org.nowstart.study.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.domain.mapper.UserMapper;
import org.nowstart.study.domain.vo.request.UserRequestVo;
import org.nowstart.study.domain.vo.response.CommResponseVo;
import org.nowstart.study.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping("/user")
    public CommResponseVo saveUser(@RequestBody @Valid UserRequestVo userRequestVo) {
        log.info("[UserController][saveUser][/user]");
        userService.saveUser(userMapper.toDto(userRequestVo));
        return CommResponseVo.builder().build();
    }

}
