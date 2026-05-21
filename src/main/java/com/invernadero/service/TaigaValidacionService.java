/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Casos de uso — validación remota con Taiga.
 */
package com.invernadero.service;

import com.invernadero.model.taiga.TaigaHistoriaUsuario;
import com.invernadero.repository.TaigaApiPort;
import java.util.Optional;
import org.springframework.stereotype.Service;

/** Expone la validación de historias de usuario contra Taiga. */
@Service
public class TaigaValidacionService {

    private final TaigaApiPort taigaApiPort;

    public TaigaValidacionService(TaigaApiPort taigaApiPort) {
        this.taigaApiPort = taigaApiPort;
    }

    public Optional<TaigaHistoriaUsuario> consultarHistoria(long userStoryId) {
        return taigaApiPort.obtenerHistoriaUsuario(userStoryId);
    }
}
