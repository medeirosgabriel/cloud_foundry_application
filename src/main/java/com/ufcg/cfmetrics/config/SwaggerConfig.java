package com.ufcg.cfmetrics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {
	
	@Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .externalDocs(new ExternalDocumentation()
                        .description("Cloud Foundry API Documentation")
                        .url("http://v3-apidocs.cloudfoundry.org/version/3.120.0/index.html"));
    }
}
