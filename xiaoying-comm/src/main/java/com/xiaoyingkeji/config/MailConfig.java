package com.xiaoyingkeji.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @description: 邮件服务配置
 * @author: zhanglei
 * @date: 2020-11-17 15:54
 **/
@Configuration
@Component
@Data
public class MailConfig {

    @Value("${mail.from}")
    private String from;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.nickName}")
    private String nickName;

    @Value("${mail.mailSubject}")
    private String mailSubject;

    @Value("${mail.port}")
    private String port;
}
