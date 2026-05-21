/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Contrato — persistencia de lecturas.
 */
package com.invernadero.repository;

import com.invernadero.model.LecturaAmbiental;
import java.util.List;
import java.util.UUID;

/** Abstrae el almacenamiento de lecturas ambientales. */
public interface LecturaPersistencePort {

    LecturaAmbiental guardar(LecturaAmbiental lectura);

    List<LecturaAmbiental> listarPorZona(UUID zonaId, int limite);

    /** Elimina todas las lecturas asociadas a una zona (antes de borrar la zona). */
    void eliminarTodasPorZona(UUID zonaId);
}
