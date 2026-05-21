/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Modelo DTO — respuesta Taiga (subset JSON).
 */
package com.invernadero.model.taiga;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Campos relevantes devueltos por {@code GET /api/v1/userstories/{id}}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaigaHistoriaUsuario {

    private long id;

    private String subject;

    private String description;

    @JsonProperty("is_closed")
    private boolean closed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
