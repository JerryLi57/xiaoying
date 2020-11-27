package com.xiaoyingkeji.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @description: 找回密码的地址配置
 * @author: zhanglei
 * @date: 2020-11-19 11:39
 **/
@Configuration
@Component
@Data
public class ForgotPasswordConfig {

    @Value("${forgetPassword.url}")
    private String url;
}
