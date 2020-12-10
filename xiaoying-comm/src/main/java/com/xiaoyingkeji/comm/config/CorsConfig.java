package com.xiaoyingkeji.comm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @description: CorsConfig
 * @author: lijiayu
 * @date: 2020-03-26 09:08
 **/
@Configuration
public class CorsConfig {

    /**
     * 设置允许跨域的源, 主用于测试
     */
    private static String[] originsVal = new String[]{
            // 这里设置的是前端端口
            "127.0.0.1:8081",
            "127.0.0.1:8188",
            "localhost:8081",
            "localhost:8188",
            "127.0.0.1:8188",
            "47.112.234.0:8188",
            "47.112.234.0:18080",
    };

    /**
     * 跨域过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        this.addAllowedOrigins(corsConfiguration);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addExposedHeader("crm-token");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

    private void addAllowedOrigins(CorsConfiguration corsConfiguration) {
        for (String origin : originsVal) {
            corsConfiguration.addAllowedOrigin("http://" + origin);
            corsConfiguration.addAllowedOrigin("https://" + origin);
        }
    }
}
