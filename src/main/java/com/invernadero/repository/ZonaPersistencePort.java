/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Contrato — persistencia de zonas.
 */
package com.invernadero.repository;

import com.invernadero.model.Zona;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Abstrae el almacenamiento de {@link Zona}. */
public interface ZonaPersistencePort {

    Zona guardar(Zona zona);

    List<Zona> listarTodas();

    Optional<Zona> buscarPorId(UUID id);

    void eliminar(UUID id);
}
