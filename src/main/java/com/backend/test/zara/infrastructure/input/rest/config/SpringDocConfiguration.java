package com.backend.test.zara.infrastructure.input.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    private final String projectVersion;
    private final String springDocVersion;

    @Autowired
    public SpringDocConfiguration(
            @Value("${project.version}") String projectVersion,
            @Value("${spring-doc.version}") String springDocVersion
    ) {
        this.projectVersion = projectVersion;
        this.springDocVersion = springDocVersion;
    }

    @Bean
    public OpenAPI api() {
        return new OpenAPI().openapi(springDocVersion).info(info());
    }

    protected Info info() {
        return new Info()
                .title("Zara API")
                .description("Api para consultar información de Zara")
                .version(projectVersion);
    }

}
