package com.xiaoyingkeji.file;

import com.xiaoyingkeji.controller.BaseControllerTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @description: 模块ControllerTest 接口
 * @author: lijiayu
 * @date: 2020-12-10 11:03:44
 **/
@Slf4j
public class FileControllerTest extends BaseControllerTest {

    @Test
    public void listByPageTest() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/file/list")
                .param("currentPage", "1")
                .param("pageSize", "5")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        ResultActions resultActions = this.mockMvc.perform(builder);
        String body = resultActions.andReturn().getResponse().getContentAsString();
        log.info("===========File=listByPageTest=body:{}", body);
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }

}