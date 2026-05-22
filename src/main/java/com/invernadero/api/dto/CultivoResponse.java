/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 */
package com.invernadero.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import java.util.UUID;

@Schema(description = "Cultivo registrado en una zona")
public class CultivoResponse {

    private UUID id;
    private UUID zonaId;
    private String nombre;
    private String variedad;
    private String notas;
    private Instant plantadoEn;
    private Instant creadoEn;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getZonaId() {
        return zonaId;
    }

    public void setZonaId(UUID zonaId) {
        this.zonaId = zonaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Instant getPlantadoEn() {
        return plantadoEn;
    }

    public void setPlantadoEn(Instant plantadoEn) {
        this.plantadoEn = plantadoEn;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Instant creadoEn) {
        this.creadoEn = creadoEn;
    }
}
