/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Mapeo REST — zona y lecturas.
 */
package com.invernadero.api.mapper;

import com.invernadero.api.dto.LecturaResponse;
import com.invernadero.api.dto.ZonaResponse;
import com.invernadero.model.LecturaAmbiental;
import com.invernadero.model.Zona;

/** Transforma modelos de dominio a DTO de respuesta HTTP. */
public final class WebDtoMapper {

    private WebDtoMapper() {}

    public static ZonaResponse toResponse(Zona z) {
        ZonaResponse r = new ZonaResponse();
        r.setId(z.getId());
        r.setNombre(z.getNombre());
        r.setDescripcion(z.getDescripcion());
        r.setCreadoEn(z.getCreadoEn());
        return r;
    }

    public static LecturaResponse toResponse(LecturaAmbiental l) {
        LecturaResponse r = new LecturaResponse();
        r.setId(l.getId());
        r.setZonaId(l.getZonaId());
        r.setTipo(l.getTipo());
        r.setValor(l.getValor());
        r.setRegistradoEn(l.getRegistradoEn());
        return r;
    }
}
