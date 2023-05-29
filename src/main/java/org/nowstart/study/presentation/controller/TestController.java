package org.nowstart.study.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.domain.mapper.UserMapper;
import org.nowstart.study.domain.vo.request.UserRequestVo;
import org.nowstart.study.domain.vo.response.UserResponseVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserMapper userMapper;

    @GetMapping("/test1")
    @Operation(summary = "Test1", description = "testController", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = @Schema(implementation = String.class)),
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)),
                    @Content(mediaType = MediaType.TEXT_XML_VALUE, schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = @Schema(implementation = String.class)),
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)),
                    @Content(mediaType = MediaType.TEXT_XML_VALUE, schema = @Schema(implementation = String.class))
            })
    })
    public String testController() {
        return "ok2";
    }

    @GetMapping("/test2")
    public String requestParamController(@RequestParam String param) {
        return param;
    }

    @GetMapping("/test3")
    public String modelAttributeController(@Valid UserRequestVo userRequestVo) {
        return userRequestVo.toString();
    }

    @PostMapping("/test4")
    @Operation(summary = "Test3", description = "requestBodyController")
    public String requestBodyController(@RequestBody @Valid UserRequestVo userRequestVo) {
        return userRequestVo.toString();
    }

    @GetMapping("/test5")
    public UserResponseVo mapstructController(@Valid UserRequestVo userRequestVo) {
        return userMapper.toVo(userMapper.toDto(userRequestVo));
    }

    @GetMapping("/test6/{id}")
    public UserResponseVo pathParamController(@Valid UserRequestVo userRequestVo) {
        return userMapper.toVo(userMapper.toDto(userRequestVo));
    }
}
