/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Punto de entrada de la aplicación Spring Boot.
 */
package com.invernadero;

import com.invernadero.config.AppProperties;
import com.invernadero.config.TaigaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Arranca el contexto de Spring para la API del invernadero.
 */
@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class, TaigaProperties.class})
public class InvernaderoApplication {

    /**
     * Método main estándar.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(InvernaderoApplication.class, args);
    }
}
