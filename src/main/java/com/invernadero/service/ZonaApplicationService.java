/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Casos de uso — zonas del invernadero.
 */
package com.invernadero.service;

import com.invernadero.model.Zona;
import com.invernadero.model.exception.RecursoNoEncontradoException;
import com.invernadero.repository.LecturaPersistencePort;
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

    public ZonaApplicationService(
            ZonaPersistencePort zonaPersistencePort, LecturaPersistencePort lecturaPersistencePort) {
        this.zonaPersistencePort = zonaPersistencePort;
        this.lecturaPersistencePort = lecturaPersistencePort;
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
        zonaPersistencePort.eliminar(id);
    }
}
