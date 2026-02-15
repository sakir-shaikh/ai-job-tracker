package com.jobtracker.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI jobTrackerOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("AI Job Tracker API")
                        .description("Backend API for tracking job applications")
                        .version("v1.0"));
    }
}