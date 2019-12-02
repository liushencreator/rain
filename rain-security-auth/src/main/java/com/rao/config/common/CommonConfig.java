package com.rao.config.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 通用配置
 * @author raojing
 * @date 2019/12/2 15:34
 */
@Configuration
public class CommonConfig {
    
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    
}
