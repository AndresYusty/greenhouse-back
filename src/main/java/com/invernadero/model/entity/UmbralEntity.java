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
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "zona_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_umbrales_ambientales_zona"))
    private ZonaEntity zona;

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

    public ZonaEntity getZona() {
        return zona;
    }

    public void setZona(ZonaEntity zona) {
        this.zona = zona;
    }

    public UUID getZonaId() {
        return zona == null ? null : zona.getId();
    }

    public void setZonaId(UUID zonaId) {
        this.zona = zonaId == null ? null : ZonaEntity.referenciaPorId(zonaId);
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
