package com.sxxy.sportplay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 全局配置处理跨域请求
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:8080","http://3s1023e183.wicp.vip","http://192.168.0.101:8080",null)
                    .allowedMethods("GET","POST","PUT","OPTIONS","DELETE")
                    // 允许是否携带信息
                    .allowCredentials(true)
                    .maxAge(3600);
    }

}
