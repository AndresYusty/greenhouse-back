/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 */
package com.invernadero.repository;

import com.invernadero.model.Cultivo;
import java.util.List;
import java.util.UUID;

public interface CultivoPersistencePort {

    Cultivo guardar(Cultivo cultivo);

    List<Cultivo> listarPorZona(UUID zonaId);

    void eliminarTodosPorZona(UUID zonaId);
}
