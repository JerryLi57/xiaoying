package com.xiaoyingkeji.controller;

import com.xiaoyingkeji.CommApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * @description: JwtException
 * @author: lijiayu
 * @date: 2020-03-18 10:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BaseControllerTest {

    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /*@Before
    public void userLogin() throws Exception {
        UserLoginDto loginDto = new UserLoginDto();
        loginDto.setUsername("admin");
        loginDto.setPassword("123456");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login/account").content(JSON.toJSONString(loginDto)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResultActions resultActions = this.mockMvc.perform(builder);
        TOKEN = resultActions.andReturn().getResponse().getHeader("egctoken");
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }*/
}
