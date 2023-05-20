package org.nowstart.study.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.domain.mapper.UserMapper;
import org.nowstart.study.domain.vo.request.UserVo;
import org.nowstart.study.domain.vo.response.UserResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserMapper userMapper;

    @GetMapping("/test1")
    public String testController() {
        return "ok1";
    }

    @GetMapping("/test2")
    public String requestParamController(@RequestParam() String param) {
        return param;
    }

    @GetMapping("/test3")
    public String modelAttributeController(@Valid UserVo userVo) {
        return userVo.toString();
    }

    @GetMapping("/test4")
    public String requestBodyController(@RequestBody @Valid UserVo userVo) {
        return userVo.toString();
    }

    @GetMapping("/test5")
    public UserResponseVo mapstructController(@Valid UserVo userVo) {
        return userMapper.toVo(userMapper.toDto(userVo));
    }

    @GetMapping("/test6/{id}")
    public UserResponseVo pathParamController(@Valid UserVo userVo) {
        return userMapper.toVo(userMapper.toDto(userVo));
    }
}
