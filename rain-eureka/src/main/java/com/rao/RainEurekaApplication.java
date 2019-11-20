package com.rao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eurake 启动类
 * @author raojing
 * @date 2019/11/20 11:35
 */
@EnableEurekaServer
@SpringBootApplication
public class RainEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RainEurekaApplication.class, args);
    }
    
}
