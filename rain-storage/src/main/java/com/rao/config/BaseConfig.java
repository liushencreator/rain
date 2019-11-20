package com.rao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Created by Lenovo on 2018/9/10.
 */
@Configuration
public class BaseConfig implements WebMvcConfigurer{

    @Resource
    private ResourceFileConfig resourceFileConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration resourceHandlerRegistration = registry.addResourceHandler("/storage/**");

        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            resourceHandlerRegistration.addResourceLocations("file:" + resourceFileConfig.getWdBasePath());
        }else{
            resourceHandlerRegistration.addResourceLocations("file:" + resourceFileConfig.getLmBasePath());
        }
    }

}
