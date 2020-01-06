package com.rao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动类
 * @author raojing
 * @date 2020/1/6 13:14
 */
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan(value = "com.rao.dao")
@SpringBootApplication
public class RainMessageApplication {

    /**
     * 短信消息
     * 服务通知推送
     * 腾讯IM通讯
     * 客服自定义消息
     * 邮件消息
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RainMessageApplication.class, args);
    }
    
}
