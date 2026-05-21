/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Propiedades de aplicación (CORS, seguridad).
 */
package com.invernadero.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuración externa tipada para la app.
 */
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Cors cors = new Cors();
    private final Security security = new Security();
    private final OAuth2 oauth2 = new OAuth2();

    public Cors getCors() {
        return cors;
    }

    public Security getSecurity() {
        return security;
    }

    public OAuth2 getOauth2() {
        return oauth2;
    }

    /** Orígenes permitidos para el SPA (separados por coma si hay varios). */
    public static class Cors {
        private String allowedOrigins = "http://localhost:5173";

        public String getAllowedOrigins() {
            return allowedOrigins;
        }

        public void setAllowedOrigins(String allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
        }

        /** Lista lista para {@link org.springframework.web.servlet.config.annotation.CorsRegistry}. */
        public String[] originArray() {
            return java.util.Arrays.stream(allowedOrigins.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toArray(String[]::new);
        }
    }

    /** Interruptor OAuth2 (Google). */
    public static class Security {
        private boolean oauth2Enabled;

        public boolean isOauth2Enabled() {
            return oauth2Enabled;
        }

        public void setOauth2Enabled(boolean oauth2Enabled) {
            this.oauth2Enabled = oauth2Enabled;
        }
    }

    /** URLs tras login OAuth2 (panel del SPA). */
    public static class OAuth2 {
        /**
         * URL completa del SPA tras Google (por defecto Vite). En Google Cloud Console la URI de callback sigue siendo
         * {@code http://localhost:8081/login/oauth2/code/google}.
         */
        private String postLoginRedirect = "http://localhost:5173/";

        public String getPostLoginRedirect() {
            return postLoginRedirect;
        }

        public void setPostLoginRedirect(String postLoginRedirect) {
            this.postLoginRedirect = postLoginRedirect;
        }
    }
}
