package com.rao.config;

import com.rao.Interceptor.BaseInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pojo.entity.resource.ResourceLocationsConfig;
import service.resource.ResourceLocationsConfigService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Lenovo on 2018/9/10.
 */
@Configuration
public class BaseConfig implements WebMvcConfigurer{

    @Bean
    public HandlerInterceptor getBaseHandler(){
        return new BaseInterceptor();
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getBaseHandler()).addPathPatterns("/**");
    }
}
