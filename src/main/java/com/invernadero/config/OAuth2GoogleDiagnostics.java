/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Ayuda a diagnosticar Error 401 invalid_client sin imprimir secretos en el log.
 */
package com.invernadero.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/** Tras el arranque, comprueba que las variables de Google existen y tienen forma esperada. */
@Component
@Profile("oauth")
public class OAuth2GoogleDiagnostics {

    private static final Logger log = LoggerFactory.getLogger(OAuth2GoogleDiagnostics.class);

    private final Environment environment;

    public OAuth2GoogleDiagnostics(Environment environment) {
        this.environment = environment;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        String id = environment.getProperty("GOOGLE_CLIENT_ID", "").strip();
        String secret = environment.getProperty("GOOGLE_CLIENT_SECRET", "").strip();

        if (id.isEmpty()) {
            log.warn(
                    "GOOGLE_CLIENT_ID esta vacio: anada GOOGLE_CLIENT_ID y GOOGLE_CLIENT_SECRET en "
                            + "src/main/resources/application-local.yml (perfil local; vea application-local.yml.example) "
                            + "o defina variables de entorno / backend/.env y reinicie.");
            return;
        }
        boolean suffixOk = id.endsWith(".apps.googleusercontent.com");
        log.info(
                "Google OAuth: client_id cargado (longitud={}, termina en .apps.googleusercontent.com={}).",
                id.length(),
                suffixOk);
        if (!suffixOk) {
            log.warn(
                    "El client_id no tiene el sufijo habitual; en Google Cloud copie el **ID de cliente** "
                            + "de una credencial tipo **Aplicacion web**, no el secreto ni otro tipo de cliente.");
        }
        if (secret.isEmpty()) {
            log.warn("GOOGLE_CLIENT_SECRET esta vacio: el intercambio de codigo fallara.");
        } else {
            log.info("Google OAuth: client_secret presente (longitud={}).", secret.length());
        }
        log.info(
                "Si Google muestra ''OAuth client was not found'': el ID no coincide con ningun cliente Web "
                        + "del proyecto, o el cliente fue borrado. URI autorizada: "
                        + "http://localhost:8081/login/oauth2/code/google");
    }
}
