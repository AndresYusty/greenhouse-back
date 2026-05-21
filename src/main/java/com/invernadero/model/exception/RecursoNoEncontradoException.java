/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Excepción de dominio — recurso no localizado.
 */
package com.invernadero.model.exception;

import java.util.UUID;

/**
 * Arrojada cuando una zona u otra entidad referenciada no existe.
 */
public class RecursoNoEncontradoException extends RuntimeException {

    private final String recurso;
    private final UUID id;

    public RecursoNoEncontradoException(String recurso, UUID id) {
        super(recurso + " no encontrado: " + id);
        this.recurso = recurso;
        this.id = id;
    }

    public String getRecurso() {
        return recurso;
    }

    public UUID getId() {
        return id;
    }
}
