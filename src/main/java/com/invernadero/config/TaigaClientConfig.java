/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Cliente REST configurado para Taiga.io.
 */
package com.invernadero.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/** Expone un {@link RestClient} apuntando a la API de Taiga. */
@Configuration
public class TaigaClientConfig {

    /**
     * Cliente HTTP base para llamadas a Taiga.
     *
     * @param properties URL base configurada
     * @return cliente listo para usar
     */
    @Bean
    @Qualifier("taigaRestClient")
    RestClient taigaRestClient(TaigaProperties properties) {
        String base = properties.getBaseUrl().replaceAll("/+$", "");
        return RestClient.builder().baseUrl(base).build();
    }
}
