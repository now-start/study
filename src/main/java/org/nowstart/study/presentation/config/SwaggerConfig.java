package org.nowstart.study.presentation.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi getBoardApi() {
        return GroupedOpenApi
                .builder()
                .group("board")
                .pathsToMatch("/board/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getTestApi() {
        return GroupedOpenApi
                .builder()
                .group("test")
                .pathsToMatch("/test*/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Study API Example")
                        .version("v0.0.1"))
                .servers(List.of(new io.swagger.v3.oas.models.servers.Server().url("/").description("Default Server url")));
    }
}