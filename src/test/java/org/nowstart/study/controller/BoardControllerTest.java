package org.nowstart.study.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.data.vo.request.BoardRequestVo;
import org.nowstart.study.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WithMockUser
@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    Mapper mapper;

    @MockBean
    BoardService boardService;

    @Test
    void findAllBoard() throws Exception {
        //given

        //when
        MvcResult result = mvc.perform(get("/board/list")
            .accept(MediaType.APPLICATION_JSON)).andReturn();

        //then
        // todo
//        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void saveBoard() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(BoardRequestVo.builder()
            .title("title")
            .contents("contents")
            .build());

        //when
        MvcResult result = mvc.perform(post("/board")
            .with(csrf())
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
        String requestBody = objectMapper.writeValueAsString(BoardRequestVo.builder()
            .title("title")
            .contents("contents")
            .build());

        //when
        MvcResult result = mvc.perform(put("/board/1")
            .with(csrf())
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
            .with(csrf())
            .accept(MediaType.APPLICATION_JSON)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }
}