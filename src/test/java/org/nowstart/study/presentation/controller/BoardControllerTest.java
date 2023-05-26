package org.nowstart.study.presentation.controller;

import org.junit.jupiter.api.Test;
import org.nowstart.study.domain.mapper.BoardMapper;
import org.nowstart.study.presentation.config.SpringSecurityConfig;
import org.nowstart.study.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest({BoardController.class, SpringSecurityConfig.class})
class BoardControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BoardMapper mapper;

    @MockBean
    BoardService boardService;

    @Test
    void findAllBoard() throws Exception {
        //given

        //when
        MvcResult result = mvc.perform(get("/board/list")
            .accept(MediaType.APPLICATION_JSON)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void saveBoard() throws Exception {
        //given
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "id");
        queryParams.add("title", "title");
        queryParams.add("writer", "writer");
        queryParams.add("contents", "contents");

        //when
        MvcResult result = mvc.perform(post("/board")
            .accept(MediaType.APPLICATION_JSON)
            .queryParams(queryParams)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void updateBoard() throws Exception {
        //given
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "id");
        queryParams.add("title", "title");
        queryParams.add("writer", "writer");
        queryParams.add("contents", "contents");

        //when
        MvcResult result = mvc.perform(put("/board/1")
            .accept(MediaType.APPLICATION_JSON)
            .queryParams(queryParams)).andReturn();

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