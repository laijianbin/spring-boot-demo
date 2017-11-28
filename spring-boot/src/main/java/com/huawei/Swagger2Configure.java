package com.huawei;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //告诉spring加载这个类的配置 
@EnableSwagger2  //启用Swagger2
public class Swagger2Configure {  
  
    @Bean  
    public Docket createRestApi() {  
        return new Docket(DocumentationType.SWAGGER_2)  
                .apiInfo(apiInfo())  
                .select()  
                .apis(RequestHandlerSelectors.basePackage("com.huawei.controller"))  
                .paths(PathSelectors.any())  
                .build();  
    }  
    /**
     * 添加Api基本信息
     */
    private ApiInfo apiInfo() {  
        return new ApiInfoBuilder()  
                .title("Spring Boot中使用Swagger2构建RESTful APIs")  
                .description("Spring Boot Swagger2")  
                .termsOfServiceUrl("http://blog.csdn.net/zhouseawater")  
                .contact("seawater")  
                .version("1.0")  
                .build();  
    }  
  
}  