package com.rao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.rao.dao")
@SpringBootApplication
public class RainAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(RainAdminApplication.class, args);
    }

}
