/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * DTO REST — registro de lectura ambiental.
 */
package com.invernadero.api.dto;

import com.invernadero.model.MetricaTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "Lectura enviada por un sensor")
public class RegistrarLecturaRequest {

    @NotNull
    private MetricaTipo tipo;

    @NotNull
    private BigDecimal valor;

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
}
