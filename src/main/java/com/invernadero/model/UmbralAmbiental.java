/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Modelo de dominio — rango de referencia ambiental por zona.
 */
package com.invernadero.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class UmbralAmbiental {

    private final UUID id;
    private final UUID zonaId;
    private final MetricaTipo tipo;
    private final BigDecimal valorMin;
    private final BigDecimal valorMax;
    private final Instant creadoEn;

    public UmbralAmbiental(
            UUID id,
            UUID zonaId,
            MetricaTipo tipo,
            BigDecimal valorMin,
            BigDecimal valorMax,
            Instant creadoEn) {
        this.id = Objects.requireNonNull(id, "id");
        this.zonaId = Objects.requireNonNull(zonaId, "zonaId");
        this.tipo = Objects.requireNonNull(tipo, "tipo");
        this.valorMin = valorMin;
        this.valorMax = valorMax;
        this.creadoEn = Objects.requireNonNull(creadoEn, "creadoEn");
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

    public BigDecimal getValorMin() {
        return valorMin;
    }

    public BigDecimal getValorMax() {
        return valorMax;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }
}
