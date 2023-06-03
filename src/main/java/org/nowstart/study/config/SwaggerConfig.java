package org.nowstart.study.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.List;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public GroupedOpenApi getUserApi() {
        return GroupedOpenApi
            .builder()
            .group("user")
            .pathsToMatch("/user/**")
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
            .components(new Components().addSecuritySchemes("JWT", new SecurityScheme()
                .name("JWT")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")))
            .info(new Info().title("Study API Example")
                .version("v0.0.1"))
            .addSecurityItem(new SecurityRequirement().addList("JWT"))
            .servers(List.of(new io.swagger.v3.oas.models.servers.Server().url("/").description("Default Server url")));
    }
}