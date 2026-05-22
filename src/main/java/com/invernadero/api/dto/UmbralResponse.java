/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 */
package com.invernadero.api.dto;

import com.invernadero.model.MetricaTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Schema(description = "Umbral almacenado por zona")
public class UmbralResponse {

    private UUID id;
    private UUID zonaId;
    private MetricaTipo tipo;
    private BigDecimal valorMin;
    private BigDecimal valorMax;
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
