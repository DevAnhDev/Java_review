package com.example.validatedocsapi.config;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class OpenAiConfig {
    @Bean
    public OpenAPI openAPI(@Value("${open.api.title}") String title,
                           @Value("${open.api.version}") String version,
                           @Value("${open.api.description}") String descriptoin,
                           @Value("${open.api.serverUrl}") String url,
                           @Value("${open.api.serverName}") String severName
                           ){
        return new OpenAPI().info(new Info()
                .title(title)
                .version(version)
                .description(descriptoin)
                .license(new License().name("API dev for you!!!").url("http://domain.vn/licenses")))
                .servers(List.of(new Server().url(url).description(severName)));
    }

    @Bean
    public GroupedOpenApi groupedOpenApi(){
        return GroupedOpenApi.builder()
                .group("api-service")
                .packagesToScan("com.example.validatedocsapi.controller")
                .build();
    }
}
