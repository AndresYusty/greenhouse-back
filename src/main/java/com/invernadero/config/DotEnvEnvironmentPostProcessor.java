/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Carga opcional de variables desde .env (varias rutas típicas de IDE y consola).
 * Las variables del SO solo se respetan si tienen un valor no vacío.
 */
package com.invernadero.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;

/**
 * Busca {@code .env} subiendo desde {@code user.dir} (p. ej. IDE en {@code frontend/} o raíz del repo).
 *
 * <p>Si Windows tiene {@code GOOGLE_CLIENT_ID} definida pero vacía, el valor del archivo .env
 * sí se usa (antes quedaba bloqueado).
 */
public class DotEnvEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String PROPERTY_SOURCE_NAME = "dotenvLocalFile";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Optional<Path> envFile = findEnvFile();
        if (envFile.isEmpty()) {
            System.err.println(
                    "[invernadero] No se encontro .env; user.dir="
                            + Path.of(System.getProperty("user.dir", ".")).toAbsolutePath()
                            + " (coloque .env en la carpeta backend o en la raiz del repo).");
            return;
        }
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            for (String raw : Files.readAllLines(envFile.get())) {
                String line = raw.strip();
                if (line.startsWith("\uFEFF")) {
                    line = line.substring(1).strip();
                }
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                int eq = line.indexOf('=');
                if (eq <= 0) {
                    continue;
                }
                String key = line.substring(0, eq).strip().replace("\uFEFF", "");
                String value = line.substring(eq + 1).strip().replace("\uFEFF", "");
                if (value.startsWith("\"") && value.endsWith("\"") && value.length() >= 2) {
                    value = value.substring(1, value.length() - 1);
                } else if (value.startsWith("'") && value.endsWith("'") && value.length() >= 2) {
                    value = value.substring(1, value.length() - 1);
                }
                if (!key.isEmpty() && useValueFromFile(key)) {
                    map.put(key, value);
                }
            }
        } catch (IOException e) {
            System.err.println("[invernadero] No se pudo leer .env: " + e.getMessage());
            return;
        }
        if (map.isEmpty()) {
            System.err.println(
                    "[invernadero] .env sin variables aplicables (o claves ya definidas en el SO con valor): "
                            + envFile.get().toAbsolutePath());
            return;
        }
        System.out.println("[invernadero] Variables cargadas desde: " + envFile.get().toAbsolutePath());
        MapPropertySource source = new MapPropertySource(PROPERTY_SOURCE_NAME, map);
        if (environment.getPropertySources().get(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME) != null) {
            environment
                    .getPropertySources()
                    .addAfter(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, source);
        } else {
            environment.getPropertySources().addLast(source);
        }
    }

    /** True si el SO no define la clave o el valor es null / solo espacios. */
    private static boolean useValueFromFile(String key) {
        String v = System.getenv(key);
        return v == null || v.isBlank();
    }

    /**
     * Sube directorios desde {@code user.dir} buscando {@code .env} y {@code backend/.env}
     * (cubre IDE con “Working directory” en raíz, en backend o en frontend).
     */
    private static Optional<Path> findEnvFile() {
        String userDir = System.getProperty("user.dir");
        if (userDir == null) {
            return Optional.empty();
        }
        Path base = Path.of(userDir).toAbsolutePath().normalize();
        for (int i = 0; i < 8 && base != null; i++) {
            Path direct = base.resolve(".env");
            if (Files.isRegularFile(direct)) {
                return Optional.of(direct);
            }
            Path underBackend = base.resolve("backend").resolve(".env");
            if (Files.isRegularFile(underBackend)) {
                return Optional.of(underBackend);
            }
            base = base.getParent();
        }
        return Optional.empty();
    }
}
