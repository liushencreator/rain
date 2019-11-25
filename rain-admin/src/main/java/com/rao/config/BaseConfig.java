package com.rao.config;

import com.rao.Interceptor.BaseInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Lenovo on 2018/9/10.
 */
@Configuration
public class BaseConfig implements WebMvcConfigurer{

    @Bean
    public HandlerInterceptor getBaseHandler(){
        return new BaseInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getBaseHandler()).addPathPatterns("/**");
    }
}
