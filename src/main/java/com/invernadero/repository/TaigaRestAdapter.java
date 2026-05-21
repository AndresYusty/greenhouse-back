/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Implementación — API REST Taiga (RestClient).
 */
package com.invernadero.repository;

import com.invernadero.config.TaigaProperties;
import com.invernadero.model.taiga.TaigaHistoriaUsuario;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClient;

/** Obtiene historias de usuario usando el token personal de Taiga. */
@Component
public class TaigaRestAdapter implements TaigaApiPort {

    private final RestClient taigaRestClient;
    private final TaigaProperties properties;

    public TaigaRestAdapter(
            @Qualifier("taigaRestClient") RestClient taigaRestClient, TaigaProperties properties) {
        this.taigaRestClient = taigaRestClient;
        this.properties = properties;
    }

    @Override
    public Optional<TaigaHistoriaUsuario> obtenerHistoriaUsuario(long userStoryId) {
        String token = properties.getPersonalAccessToken();
        if (token == null || token.isBlank()) {
            return Optional.empty();
        }
        try {
            TaigaHistoriaUsuario body = taigaRestClient
                    .get()
                    .uri("/userstories/{id}", userStoryId)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .body(TaigaHistoriaUsuario.class);
            return Optional.ofNullable(body);
        } catch (RestClientException ex) {
            return Optional.empty();
        }
    }
}
