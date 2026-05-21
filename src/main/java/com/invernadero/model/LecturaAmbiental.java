/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Modelo de dominio — lectura de sensor ambiental.
 */
package com.invernadero.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Lectura puntual asociada a una zona (dominio puro).
 */
public final class LecturaAmbiental {

    private final UUID id;
    private final UUID zonaId;
    private final MetricaTipo tipo;
    private final BigDecimal valor;
    private final Instant registradoEn;

    public LecturaAmbiental(UUID id, UUID zonaId, MetricaTipo tipo, BigDecimal valor, Instant registradoEn) {
        this.id = Objects.requireNonNull(id, "id");
        this.zonaId = Objects.requireNonNull(zonaId, "zonaId");
        this.tipo = Objects.requireNonNull(tipo, "tipo");
        this.valor = Objects.requireNonNull(valor, "valor");
        this.registradoEn = Objects.requireNonNull(registradoEn, "registradoEn");
    }

    public UUID getId() {
        return id;
    }

    public UUID getZonaId() {
        return zonaId;
    }

    public MetricaTipo getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Instant getRegistradoEn() {
        return registradoEn;
    }
}
