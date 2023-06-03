package org.nowstart.study.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.dto.UserDto;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.data.vo.request.UserRequestVo;
import org.nowstart.study.data.vo.response.CommResponseVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final Mapper mapper;

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
    public CommResponseVo<UserDto> mapstructController(@Valid UserRequestVo userRequestVo) {
        return CommResponseVo.<UserDto>builder()
            .resultSet(List.of(mapper.toDto(userRequestVo)))
            .build();
    }

    @GetMapping("/test6/{id}")
    public CommResponseVo<UserDto> pathParamController(@Valid UserRequestVo userRequestVo) {
        return CommResponseVo.<UserDto>builder()
            .resultSet(List.of(mapper.toDto(userRequestVo)))
            .build();
    }
}
