/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 */
package com.invernadero.api.dto;

import com.invernadero.model.MetricaTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "Rango de referencia ambiental por métrica (al menos uno de min/max)")
public class DefinirUmbralRequest {

    @NotNull
    private MetricaTipo tipo;

    private BigDecimal valorMin;
    private BigDecimal valorMax;

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
}
