package com.iiiiii.accbooksecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8112")  // Swagger UI가 동작하는 곳의 URL
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
