/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 */
package com.invernadero.repository.mapper;

import com.invernadero.model.Cultivo;
import com.invernadero.model.entity.CultivoEntity;

public final class CultivoMapper {

    private CultivoMapper() {}

    public static Cultivo toDomain(CultivoEntity e) {
        return new Cultivo(
                e.getId(),
                e.getZonaId(),
                e.getNombre(),
                e.getVariedad() != null ? e.getVariedad() : "",
                e.getNotas() != null ? e.getNotas() : "",
                e.getPlantadoEn(),
                e.getCreadoEn());
    }

    public static CultivoEntity toEntity(Cultivo c) {
        CultivoEntity e = new CultivoEntity();
        e.setId(c.getId());
        e.setZonaId(c.getZonaId());
        e.setNombre(c.getNombre());
        e.setVariedad(c.getVariedad());
        e.setNotas(c.getNotas());
        e.setPlantadoEn(c.getPlantadoEn());
        e.setCreadoEn(c.getCreadoEn());
        return e;
    }
}
