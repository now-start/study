package org.nowstart.study.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nowstart.study.config.SpringSecurityConfig;
import org.nowstart.study.data.mapper.BoardMapper;
import org.nowstart.study.data.vo.request.BoardRequestVo;
import org.nowstart.study.service.BoardService;
import org.nowstart.study.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest({BoardController.class, SpringSecurityConfig.class})
class BoardControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BoardMapper mapper;

    @MockBean
    BoardService boardService;

    @BeforeEach
    void init() {
        given(JwtUtil.createJwt(any(), any(), any())).willReturn("test");
    }

    @Test
    void findAllBoard() throws Exception {
        //given

        //when
        MvcResult result = mvc.perform(get("/board/list")
            .header(HttpHeaders.AUTHORIZATION, "Bearer")
            .accept(MediaType.APPLICATION_JSON)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void saveBoard() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(new BoardRequestVo("title", "writer", "contents"));

        //when
        MvcResult result = mvc.perform(post("/board")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void updateBoard() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(new BoardRequestVo("title", "writer", "contents"));

        //when
        MvcResult result = mvc.perform(put("/board/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void deleteBoard() throws Exception {
        //given

        //when
        MvcResult result = mvc.perform(delete("/board/2")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }
}