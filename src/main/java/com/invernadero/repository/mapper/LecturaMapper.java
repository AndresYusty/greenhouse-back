/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Mapeo dominio ↔ entidad JPA (lectura).
 */
package com.invernadero.repository.mapper;

import com.invernadero.model.LecturaAmbiental;
import com.invernadero.model.entity.LecturaEntity;

/** Convierte lecturas entre capas. */
public final class LecturaMapper {

    private LecturaMapper() {}

    public static LecturaAmbiental toDomain(LecturaEntity e) {
        if (e == null) {
            return null;
        }
        return new LecturaAmbiental(e.getId(), e.getZonaId(), e.getTipo(), e.getValor(), e.getRegistradoEn());
    }

    public static LecturaEntity toEntity(LecturaAmbiental l) {
        LecturaEntity e = new LecturaEntity();
        e.setId(l.getId());
        e.setZonaId(l.getZonaId());
        e.setTipo(l.getTipo());
        e.setValor(l.getValor());
        e.setRegistradoEn(l.getRegistradoEn());
        return e;
    }
}
