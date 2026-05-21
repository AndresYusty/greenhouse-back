/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Entidad JPA — lectura ambiental.
 */
package com.invernadero.model.entity;

import com.invernadero.model.MetricaTipo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "lecturas_ambientales")
public class LecturaEntity {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(name = "zona_id", nullable = false)
    private UUID zonaId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private MetricaTipo tipo;

    @Column(nullable = false, precision = 12, scale = 4)
    private BigDecimal valor;

    @Column(name = "registrado_en", nullable = false)
    private Instant registradoEn;

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

    public MetricaTipo getTipo() {
        return tipo;
    }

    public void setTipo(MetricaTipo tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Instant getRegistradoEn() {
        return registradoEn;
    }

    public void setRegistradoEn(Instant registradoEn) {
        this.registradoEn = registradoEn;
    }
}
