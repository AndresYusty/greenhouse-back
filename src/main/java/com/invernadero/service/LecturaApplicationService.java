/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Casos de uso — lecturas ambientales.
 */
package com.invernadero.service;

import com.invernadero.model.LecturaAmbiental;
import com.invernadero.model.MetricaTipo;
import com.invernadero.model.exception.RecursoNoEncontradoException;
import com.invernadero.repository.LecturaPersistencePort;
import com.invernadero.repository.ZonaPersistencePort;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Registra y consulta lecturas garantizando que la zona exista. */
@Service
@Transactional
public class LecturaApplicationService {

    private final LecturaPersistencePort lecturaPersistencePort;
    private final ZonaPersistencePort zonaPersistencePort;

    public LecturaApplicationService(
            LecturaPersistencePort lecturaPersistencePort, ZonaPersistencePort zonaPersistencePort) {
        this.lecturaPersistencePort = lecturaPersistencePort;
        this.zonaPersistencePort = zonaPersistencePort;
    }

    public LecturaAmbiental registrar(UUID zonaId, MetricaTipo tipo, BigDecimal valor) {
        if (zonaPersistencePort.buscarPorId(zonaId).isEmpty()) {
            throw new RecursoNoEncontradoException("Zona", zonaId);
        }
        LecturaAmbiental lectura = new LecturaAmbiental(UUID.randomUUID(), zonaId, tipo, valor, Instant.now());
        return lecturaPersistencePort.guardar(lectura);
    }

    @Transactional(readOnly = true)
    public List<LecturaAmbiental> listarPorZona(UUID zonaId, int limite) {
        if (zonaPersistencePort.buscarPorId(zonaId).isEmpty()) {
            throw new RecursoNoEncontradoException("Zona", zonaId);
        }
        return lecturaPersistencePort.listarPorZona(zonaId, limite);
    }
}
