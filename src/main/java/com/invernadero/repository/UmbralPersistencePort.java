/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 */
package com.invernadero.repository;

import com.invernadero.model.MetricaTipo;
import com.invernadero.model.UmbralAmbiental;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UmbralPersistencePort {

    UmbralAmbiental guardar(UmbralAmbiental umbral);

    List<UmbralAmbiental> listarPorZona(UUID zonaId);

    Optional<UmbralAmbiental> buscarPorZonaYTipo(UUID zonaId, MetricaTipo tipo);

    void eliminarTodosPorZona(UUID zonaId);
}
