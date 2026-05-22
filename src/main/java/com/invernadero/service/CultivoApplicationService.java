/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Casos de uso — cultivos por zona.
 */
package com.invernadero.service;

import com.invernadero.model.Cultivo;
import com.invernadero.model.exception.RecursoNoEncontradoException;
import com.invernadero.repository.CultivoPersistencePort;
import com.invernadero.repository.ZonaPersistencePort;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CultivoApplicationService {

    private final CultivoPersistencePort cultivoPersistencePort;
    private final ZonaPersistencePort zonaPersistencePort;

    public CultivoApplicationService(
            CultivoPersistencePort cultivoPersistencePort, ZonaPersistencePort zonaPersistencePort) {
        this.cultivoPersistencePort = cultivoPersistencePort;
        this.zonaPersistencePort = zonaPersistencePort;
    }

    public Cultivo registrar(UUID zonaId, String nombre, String variedad, String notas, Instant plantadoEn) {
        if (zonaPersistencePort.buscarPorId(zonaId).isEmpty()) {
            throw new RecursoNoEncontradoException("Zona", zonaId);
        }
        Instant plantado = plantadoEn != null ? plantadoEn : Instant.now();
        Cultivo c = new Cultivo(
                UUID.randomUUID(),
                zonaId,
                nombre.trim(),
                variedad != null ? variedad.trim() : "",
                notas != null ? notas.trim() : "",
                plantado,
                Instant.now());
        return cultivoPersistencePort.guardar(c);
    }

    @Transactional(readOnly = true)
    public List<Cultivo> listarPorZona(UUID zonaId) {
        if (zonaPersistencePort.buscarPorId(zonaId).isEmpty()) {
            throw new RecursoNoEncontradoException("Zona", zonaId);
        }
        return cultivoPersistencePort.listarPorZona(zonaId);
    }
}
