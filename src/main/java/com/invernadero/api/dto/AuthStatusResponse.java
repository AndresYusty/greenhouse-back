/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Estado de sesión expuesto al SPA (OAuth2 Google opcional).
 */
package com.invernadero.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Respuesta de {@code GET /api/v1/auth/status}.
 *
 * @param authenticated Si la petición actual tiene sesión OAuth2 válida (o modo abierto sin OAuth).
 * @param oauth2Enabled Si el backend exige OAuth2 para {@code /api/**}.
 * @param loginUrl Ruta para iniciar login Google (solo cuando OAuth está activo y no hay sesión).
 * @param email Email del usuario OAuth (si hay sesión).
 * @param name Nombre mostrado del usuario OAuth (si hay sesión).
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthStatusResponse(
        boolean authenticated,
        boolean oauth2Enabled,
        String loginUrl,
        String email,
        String name) {

    /** Modo desarrollo / demo: API abierta, sin pantalla de login. */
    public static AuthStatusResponse openMode() {
        return new AuthStatusResponse(true, false, null, null, null);
    }
}
