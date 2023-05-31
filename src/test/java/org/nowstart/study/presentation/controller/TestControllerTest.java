package org.nowstart.study.presentation.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.nowstart.study.config.SpringSecurityConfig;
import org.nowstart.study.controller.TestController;
import org.nowstart.study.data.mapper.UserMapper;
import org.nowstart.study.data.vo.request.UserRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@WebMvcTest({TestController.class, SpringSecurityConfig.class})
class TestControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserMapper mapper;

    @Test
    void testController() throws Exception {
        //given

        //when
        MvcResult result = mvc.perform(get("/test1")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @ParameterizedTest
    @CsvSource({"test1", "test2", "test3"})
    void requestParamController(String queryString) throws Exception {
        //given
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("param", queryString);

        //when
        MvcResult result = mvc.perform(get("/test2")
                .accept(MediaType.APPLICATION_JSON)
                .queryParams(queryParams)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isEqualTo(queryString);
    }

    @ParameterizedTest
    @CsvSource({"testId1, testPassword1, testName1", "testId2, testPassword2, testName1", "testId3, testPassword3, testName1"})
    void modelAttributeController(String id, String password, String name) throws Exception {
        //given
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", id);
        queryParams.add("password", password);
        queryParams.add("name", name);

        //when
        MvcResult result = mvc.perform(get("/test3")
                .accept(MediaType.APPLICATION_JSON)
                .queryParams(queryParams)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).contains(id, password);
    }

    @ParameterizedTest
    @CsvSource({"testId1, testPassword1, testName1", "testId2, testPassword2, testName1", "testId3, testPassword3, testName1"})
    void requestBodyController(String id, String password, String name) throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(new UserRequestVo(id, password, name));

        //when
        MvcResult result = mvc.perform(post("/test4")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).contains(id, password);
    }

    @Test
    void mapstructController() throws Exception {
        //given
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "testId");
        queryParams.add("password", "testPw");
        queryParams.add("name", "testName");

        //when
        MvcResult result = mvc.perform(get("/test5")
                .accept(MediaType.APPLICATION_JSON)
                .queryParams(queryParams)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @ParameterizedTest
    @CsvSource({"123", "234", "345"})
    void pathParamController(String id) throws Exception {
        //given
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", id);
        queryParams.add("password", "test");
        queryParams.add("name", "testName");

        //when
        MvcResult result = mvc.perform(get("/test6/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .queryParams(queryParams)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @ParameterizedTest
    @CsvSource({",", ",testPw", "testId,", "'',''", "'',testPw", "testId,''", "1,testPw"})
    void BindingException_notBlank(String id, String password) throws Exception {
        //given
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", id);
        queryParams.add("password", password);

        //when
        MvcResult result = mvc.perform(get("/test5")
                .accept(MediaType.APPLICATION_JSON)
                .queryParams(queryParams)).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(400);
        assertThat(result.getResponse().getContentAsString()).containsAnyOf("5000", "5001");
    }
}