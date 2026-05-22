/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Modelo de dominio — cultivo en una zona.
 */
package com.invernadero.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class Cultivo {

    private final UUID id;
    private final UUID zonaId;
    private final String nombre;
    private final String variedad;
    private final String notas;
    private final Instant plantadoEn;
    private final Instant creadoEn;

    public Cultivo(
            UUID id,
            UUID zonaId,
            String nombre,
            String variedad,
            String notas,
            Instant plantadoEn,
            Instant creadoEn) {
        this.id = Objects.requireNonNull(id, "id");
        this.zonaId = Objects.requireNonNull(zonaId, "zonaId");
        this.nombre = Objects.requireNonNull(nombre, "nombre");
        this.variedad = variedad != null ? variedad : "";
        this.notas = notas != null ? notas : "";
        this.plantadoEn = Objects.requireNonNull(plantadoEn, "plantadoEn");
        this.creadoEn = Objects.requireNonNull(creadoEn, "creadoEn");
    }

    public UUID getId() {
        return id;
    }

    public UUID getZonaId() {
        return zonaId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getVariedad() {
        return variedad;
    }

    public String getNotas() {
        return notas;
    }

    public Instant getPlantadoEn() {
        return plantadoEn;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }
}
