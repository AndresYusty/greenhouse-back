/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 */
package com.invernadero.repository.mapper;

import com.invernadero.model.UmbralAmbiental;
import com.invernadero.model.entity.UmbralEntity;

public final class UmbralMapper {

    private UmbralMapper() {}

    public static UmbralAmbiental toDomain(UmbralEntity e) {
        return new UmbralAmbiental(e.getId(), e.getZonaId(), e.getTipo(), e.getValorMin(), e.getValorMax(), e.getCreadoEn());
    }

    public static UmbralEntity toEntity(UmbralAmbiental u) {
        UmbralEntity e = new UmbralEntity();
        e.setId(u.getId());
        e.setZonaId(u.getZonaId());
        e.setTipo(u.getTipo());
        e.setValorMin(u.getValorMin());
        e.setValorMax(u.getValorMax());
        e.setCreadoEn(u.getCreadoEn());
        return e;
    }
}
