package com.rao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 支付系统-启动类
 * @author raojing
 * @date 2020/1/8 15:07
 */
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan(value = "com.rao.dao")
@SpringBootApplication
public class RainPaymentApplication {

    /**
     * 支付
     * 钱包支付
     * 微信支付
     * 支付宝支付
     * 银联支付
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RainPaymentApplication.class, args);
    }
    
}
