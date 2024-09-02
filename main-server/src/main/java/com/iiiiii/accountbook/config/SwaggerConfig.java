package com.iiiiii.accountbook.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "SavePig API 명세서",
                description = "SavePig API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {

}