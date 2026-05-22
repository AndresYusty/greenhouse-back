/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Casos de uso — zonas del invernadero.
 */
package com.invernadero.service;

import com.invernadero.model.Zona;
import com.invernadero.model.exception.RecursoNoEncontradoException;
import com.invernadero.repository.CultivoPersistencePort;
import com.invernadero.repository.LecturaPersistencePort;
import com.invernadero.repository.UmbralPersistencePort;
import com.invernadero.repository.ZonaPersistencePort;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Orquesta operaciones sobre zonas. */
@Service
@Transactional
public class ZonaApplicationService {

    private final ZonaPersistencePort zonaPersistencePort;
    private final LecturaPersistencePort lecturaPersistencePort;
    private final CultivoPersistencePort cultivoPersistencePort;
    private final UmbralPersistencePort umbralPersistencePort;

    public ZonaApplicationService(
            ZonaPersistencePort zonaPersistencePort,
            LecturaPersistencePort lecturaPersistencePort,
            CultivoPersistencePort cultivoPersistencePort,
            UmbralPersistencePort umbralPersistencePort) {
        this.zonaPersistencePort = zonaPersistencePort;
        this.lecturaPersistencePort = lecturaPersistencePort;
        this.cultivoPersistencePort = cultivoPersistencePort;
        this.umbralPersistencePort = umbralPersistencePort;
    }

    public Zona crear(String nombre, String descripcion) {
        Zona zona = new Zona(UUID.randomUUID(), nombre.trim(), descripcion != null ? descripcion.trim() : "", Instant.now());
        return zonaPersistencePort.guardar(zona);
    }

    @Transactional(readOnly = true)
    public List<Zona> listar() {
        return zonaPersistencePort.listarTodas();
    }

    @Transactional(readOnly = true)
    public Zona obtener(UUID id) {
        return zonaPersistencePort.buscarPorId(id).orElseThrow(() -> new RecursoNoEncontradoException("Zona", id));
    }

    public void eliminar(UUID id) {
        obtener(id);
        lecturaPersistencePort.eliminarTodasPorZona(id);
        cultivoPersistencePort.eliminarTodosPorZona(id);
        umbralPersistencePort.eliminarTodosPorZona(id);
        zonaPersistencePort.eliminar(id);
    }
}
