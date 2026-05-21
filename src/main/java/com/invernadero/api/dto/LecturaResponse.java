/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * DTO REST — lectura ambiental.
 */
package com.invernadero.api.dto;

import com.invernadero.model.MetricaTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Schema(description = "Lectura almacenada")
public class LecturaResponse {

    private UUID id;
    private UUID zonaId;
    private MetricaTipo tipo;
    private BigDecimal valor;
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
