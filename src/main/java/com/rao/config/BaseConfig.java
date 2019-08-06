package com.rao.config;

import com.rao.Interceptor.BaseInterceptor;
import com.rao.bean.resource.ResourceLocationsConfig;
import com.rao.service.resource.ResourceLocationsConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Lenovo on 2018/9/10.
 */
@Configuration
public class BaseConfig implements WebMvcConfigurer{

    @Resource
    private ResourceLocationsConfigService resourceLocationsConfigService;

    @Bean
    public HandlerInterceptor getBaseHandler(){
        return new BaseInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration resourceHandlerRegistration = registry.addResourceHandler("/source/**");
        List<ResourceLocationsConfig> servicePaths = resourceLocationsConfigService.findAll();

        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            servicePaths.forEach(item -> {
                resourceHandlerRegistration.addResourceLocations("file:" + item.getWdLocationPath());
            });
        }else{
            servicePaths.forEach(item -> {
                resourceHandlerRegistration.addResourceLocations("file:" + item.getLmLocationPath());
            });
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getBaseHandler()).addPathPatterns("/**");
    }
}
