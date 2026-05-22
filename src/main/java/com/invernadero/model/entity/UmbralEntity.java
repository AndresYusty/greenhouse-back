/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Entidad JPA — rango recomendado por zona y tipo de métrica.
 */
package com.invernadero.model.entity;

import com.invernadero.model.MetricaTipo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "umbrales_ambientales",
        uniqueConstraints = @UniqueConstraint(name = "uk_umbral_zona_metrica", columnNames = {"zona_id", "tipo"}))
public class UmbralEntity {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(name = "zona_id", nullable = false)
    private UUID zonaId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private MetricaTipo tipo;

    @Column(name = "valor_min", precision = 12, scale = 4)
    private BigDecimal valorMin;

    @Column(name = "valor_max", precision = 12, scale = 4)
    private BigDecimal valorMax;

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

    public MetricaTipo getTipo() {
        return tipo;
    }

    public void setTipo(MetricaTipo tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValorMin() {
        return valorMin;
    }

    public void setValorMin(BigDecimal valorMin) {
        this.valorMin = valorMin;
    }

    public BigDecimal getValorMax() {
        return valorMax;
    }

    public void setValorMax(BigDecimal valorMax) {
        this.valorMax = valorMax;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Instant creadoEn) {
        this.creadoEn = creadoEn;
    }
}
