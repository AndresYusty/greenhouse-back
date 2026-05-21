/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Propiedades de integración con Taiga.io.
 */
package com.invernadero.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * URL base y token para la API REST de Taiga.
 */
@ConfigurationProperties(prefix = "app.taiga")
public class TaigaProperties {

    /** Base URL, por ejemplo https://api.taiga.io/api/v1 */
    private String baseUrl = "https://api.taiga.io/api/v1";

    /** Token personal generado en Taiga (no versionar). */
    private String personalAccessToken = "";

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getPersonalAccessToken() {
        return personalAccessToken;
    }

    public void setPersonalAccessToken(String personalAccessToken) {
        this.personalAccessToken = personalAccessToken;
    }
}
