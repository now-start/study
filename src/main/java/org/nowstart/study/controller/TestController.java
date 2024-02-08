package org.nowstart.study.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.mapper.Mapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final Mapper mapper;

    @GetMapping("test/status")
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
}
