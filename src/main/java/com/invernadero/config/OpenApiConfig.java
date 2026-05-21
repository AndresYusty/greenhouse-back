/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Metadatos OpenAPI (SpringDoc).
 */
package com.invernadero.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Expone documentación interactiva en {@code /swagger-ui.html}.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Bean principal de OpenAPI / Appidocs.
     *
     * @return descripción de la API
     */
    @Bean
    public OpenAPI invernaderoOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Invernadero API")
                        .version("1.0")
                        .description("REST API para automatización y monitoreo del invernadero.")
                        .license(new License().name("Academic").url("https://opensource.org/licenses/MIT"))
                        .contact(new Contact().name("Equipo Invernadero")));
    }
}
