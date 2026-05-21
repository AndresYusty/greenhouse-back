/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * API REST — validación remota con Taiga.
 */
package com.invernadero.api;

import com.invernadero.model.taiga.TaigaHistoriaUsuario;
import com.invernadero.service.TaigaValidacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/taiga")
@Tag(name = "Taiga", description = "Integración con historias de usuario (Taiga.io)")
public class TaigaController {

    private final TaigaValidacionService taigaValidacionService;
    private final MessageSource messageSource;

    public TaigaController(TaigaValidacionService taigaValidacionService, MessageSource messageSource) {
        this.taigaValidacionService = taigaValidacionService;
        this.messageSource = messageSource;
    }

    @GetMapping("/historias/{id}")
    @Operation(summary = "Obtener historia de usuario desde Taiga")
    public ResponseEntity<TaigaHistoriaUsuario> historia(@PathVariable("id") long userStoryId) {
        return taigaValidacionService
                .consultarHistoria(userStoryId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        messageSource.getMessage(
                                "error.taiga", null, LocaleContextHolder.getLocale())));
    }
}
