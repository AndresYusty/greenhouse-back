/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Casos de uso — umbrales recomendados por zona y métrica.
 */
package com.invernadero.service;

import com.invernadero.model.MetricaTipo;
import com.invernadero.model.UmbralAmbiental;
import com.invernadero.model.exception.RecursoNoEncontradoException;
import com.invernadero.repository.UmbralPersistencePort;
import com.invernadero.repository.ZonaPersistencePort;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UmbralApplicationService {

    private final UmbralPersistencePort umbralPersistencePort;
    private final ZonaPersistencePort zonaPersistencePort;

    public UmbralApplicationService(
            UmbralPersistencePort umbralPersistencePort, ZonaPersistencePort zonaPersistencePort) {
        this.umbralPersistencePort = umbralPersistencePort;
        this.zonaPersistencePort = zonaPersistencePort;
    }

    public UmbralAmbiental definir(UUID zonaId, MetricaTipo tipo, BigDecimal valorMin, BigDecimal valorMax) {
        if (zonaPersistencePort.buscarPorId(zonaId).isEmpty()) {
            throw new RecursoNoEncontradoException("Zona", zonaId);
        }
        if (valorMin == null && valorMax == null) {
            throw new IllegalArgumentException("Indique al menos valorMin o valorMax");
        }
        if (valorMin != null && valorMax != null && valorMin.compareTo(valorMax) > 0) {
            throw new IllegalArgumentException("valorMin no puede superar valorMax");
        }
        UmbralAmbiental previous =
                umbralPersistencePort.buscarPorZonaYTipo(zonaId, tipo).orElse(null);
        UUID id = previous != null ? previous.getId() : UUID.randomUUID();
        Instant creadoEn = previous != null ? previous.getCreadoEn() : Instant.now();
        UmbralAmbiental u = new UmbralAmbiental(id, zonaId, tipo, valorMin, valorMax, creadoEn);
        return umbralPersistencePort.guardar(u);
    }

    @Transactional(readOnly = true)
    public List<UmbralAmbiental> listarPorZona(UUID zonaId) {
        if (zonaPersistencePort.buscarPorId(zonaId).isEmpty()) {
            throw new RecursoNoEncontradoException("Zona", zonaId);
        }
        return umbralPersistencePort.listarPorZona(zonaId);
    }
}
