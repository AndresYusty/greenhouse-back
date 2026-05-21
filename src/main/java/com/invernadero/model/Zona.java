/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Modelo de dominio — zona física del invernadero.
 */
package com.invernadero.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Zona donde se ubican cultivos y sensores (sin anotaciones JPA — núcleo del dominio).
 */
public final class Zona {

    private final UUID id;
    private final String nombre;
    private final String descripcion;
    private final Instant creadoEn;

    public Zona(UUID id, String nombre, String descripcion, Instant creadoEn) {
        this.id = Objects.requireNonNull(id, "id");
        this.nombre = Objects.requireNonNull(nombre, "nombre");
        this.descripcion = descripcion != null ? descripcion : "";
        this.creadoEn = Objects.requireNonNull(creadoEn, "creadoEn");
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }
}
