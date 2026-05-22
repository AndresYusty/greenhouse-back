/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 */
package com.invernadero.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Schema(description = "Alta de cultivo en una zona")
public class CrearCultivoRequest {

    @NotBlank
    @Size(max = 200)
    private String nombre;

    @Size(max = 120)
    private String variedad;

    @Size(max = 2000)
    private String notas;

    private Instant plantadoEn;

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
}
