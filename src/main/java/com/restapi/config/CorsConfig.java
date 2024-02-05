package com.restapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements  WebMvcConfigurer{

    // Using properties to dynamically set allowed HTTP methods
    @Value("${cors.allowed-methods}")
    private String allowedMethods;

    // Override the addCorsMappings method to configure CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allowing CORS for all endpoints and specifying allowed HTTP methods dynamically
        registry.addMapping("/**")
                .allowedMethods(allowedMethods.split(","));
    }
}
