package com.rao.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName : Swagger2Config  //类名
 * @Description : swagger的配置  //描述
 * @Author : xujianjie  //作者
 * @Date: 2019-12-31 16:49  //时间
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.rao.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("窗帘项目文档")
                .description("窗帘项目文档 API 1.0 操作文档")
                //服务条款网址
                .termsOfServiceUrl("https://www.cnblogs.com/wadmwz/")
                .version("1.0")
//                .contact(new Contact("王智家园", "https://www.cnblogs.com/wadmwz/", "15713598138@sina.cn"))
                .build();
    }
}
