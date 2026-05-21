/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Redirección tras login OAuth2 (vuelta al SPA).
 */
package com.invernadero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Tras autenticación correcta con Google, envía al navegador a la URL del panel (p. ej. Vite en 5173).
 */
@Configuration
public class OAuth2FlowConfig {

    @Bean
    AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler(AppProperties appProperties) {
        return (request, response, authentication) ->
                response.sendRedirect(appProperties.getOauth2().getPostLoginRedirect());
    }
}
