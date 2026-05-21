/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Mapeo dominio ↔ entidad JPA (zona).
 */
package com.invernadero.repository.mapper;

import com.invernadero.model.Zona;
import com.invernadero.model.entity.ZonaEntity;

/** Convierte entre modelo de dominio y persistencia. */
public final class ZonaMapper {

    private ZonaMapper() {}

    public static Zona toDomain(ZonaEntity e) {
        if (e == null) {
            return null;
        }
        return new Zona(e.getId(), e.getNombre(), e.getDescripcion(), e.getCreadoEn());
    }

    public static ZonaEntity toEntity(Zona z) {
        ZonaEntity e = new ZonaEntity();
        e.setId(z.getId());
        e.setNombre(z.getNombre());
        e.setDescripcion(z.getDescripcion());
        e.setCreadoEn(z.getCreadoEn());
        return e;
    }
}
