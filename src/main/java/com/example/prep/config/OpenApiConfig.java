package com.example.prep.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Java Prep — Securities Finance API")
                        .description("Built day-by-day as Jefferies interview preparation")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Sumit Motghare")
                                .email("sumitmotghare7292@gmail.com")));
    }
}
