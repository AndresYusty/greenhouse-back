/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Entidad JPA — cultivo asociado a una zona.
 */
package com.invernadero.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "cultivos")
public class CultivoEntity {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(name = "zona_id", nullable = false)
    private UUID zonaId;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(length = 120)
    private String variedad;

    @Column(length = 2000)
    private String notas;

    @Column(name = "plantado_en", nullable = false)
    private Instant plantadoEn;

    @Column(name = "creado_en", nullable = false)
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
