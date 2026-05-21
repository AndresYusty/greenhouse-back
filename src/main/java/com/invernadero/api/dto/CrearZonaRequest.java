/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * DTO REST — creación de zona.
 */
package com.invernadero.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos para crear una zona del invernadero")
public class CrearZonaRequest {

    @NotBlank
    @Size(max = 120)
    private String nombre;

    @Size(max = 2000)
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
