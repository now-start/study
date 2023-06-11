package org.nowstart.study.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.dto.user.UserSaveDto;
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
    public CommResponseVo<UserSaveDto> saveUser(@RequestBody @Valid UserRequestVo userRequestVo) {
        log.info("[UserController][saveUser][/user]");
        userService.saveUser(mapper.toServiceDto(userRequestVo));
        return CommResponseVo.<UserSaveDto>builder().build();
    }

}
