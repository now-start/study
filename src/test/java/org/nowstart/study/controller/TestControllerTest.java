package org.nowstart.study.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.nowstart.study.data.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WithMockUser
@WebMvcTest(value = TestController.class)
class TestControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    Mapper mapper;

    @Test
    void testController() throws Exception {
        //given

        //when
        MvcResult result = mvc.perform(get("/test/status")
            .accept(MediaType.APPLICATION_JSON)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }
}