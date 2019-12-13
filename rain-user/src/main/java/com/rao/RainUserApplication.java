package com.rao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动类
 * @author raojing
 * @date 2019/11/25 21:33
 */
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan(value = "com.rao.dao")
@SpringBootApplication
public class RainUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(RainUserApplication.class, args);
    }

}
