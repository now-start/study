package org.nowstart.study.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.nowstart.study.config.SpringSecurityConfig;
import org.nowstart.study.data.mapper.UserMapper;
import org.nowstart.study.data.vo.request.UserRequestVo;
import org.nowstart.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest({UserController.class, SpringSecurityConfig.class})
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserMapper mapper;

    @MockBean
    UserService service;

    @Test
    void saveUser() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(new UserRequestVo("testId", "password", "name"));


        //when
        MvcResult result = mvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }
}