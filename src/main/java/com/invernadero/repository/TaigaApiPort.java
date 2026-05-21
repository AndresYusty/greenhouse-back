/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Contrato — cliente REST Taiga (historias de usuario).
 */
package com.invernadero.repository;

import com.invernadero.model.taiga.TaigaHistoriaUsuario;
import java.util.Optional;

/** Consulta historias de usuario en Taiga para validar criterios de aceptación. */
public interface TaigaApiPort {

    /**
     * Obtiene una historia por su identificador numérico en Taiga.
     *
     * @param userStoryId id de la US en Taiga
     * @return datos si existe y hay token configurado
     */
    Optional<TaigaHistoriaUsuario> obtenerHistoriaUsuario(long userStoryId);
}
