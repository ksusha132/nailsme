package com.nails.nastya.nailsme.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    private final BuildProperties buildProperties;

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Nails me API")
                        .description("Сервис записи на маникюр")
                        .version("v" + buildProperties.getVersion()))
                .externalDocs(new ExternalDocumentation()
                        .description("-"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("nailsme.ru")
                .pathsToMatch("/api/v*/**")
                .build();
    }
}