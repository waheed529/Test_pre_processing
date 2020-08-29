package com.test.swagger;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@SpringBootConfiguration
@EnableSwagger2
public class Swagger2UiConfiguration extends WebMvcConfigurerAdapter 
{
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.test.wah.controller"))
                //.paths(PathSelectors.any())
                 .paths(PathSelectors.ant("/**"))
                .build();
    }
 
}