package com.rao.config;

import com.rao.filter.CorsWebFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 * @author raojing
 * @date 2019/11/23 14:42
 */
@Configuration
public class FilterConfig {

    @Bean
    public CorsWebFilter corsFilter(){
        return new CorsWebFilter();
    }

}
