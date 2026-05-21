/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * API REST — lecturas ambientales.
 */
package com.invernadero.api;

import com.invernadero.api.dto.LecturaResponse;
import com.invernadero.api.dto.RegistrarLecturaRequest;
import com.invernadero.api.mapper.WebDtoMapper;
import com.invernadero.service.LecturaApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/zonas/{zonaId}/lecturas", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Lecturas", description = "Lecturas ambientales por zona")
public class LecturaController {

    private final LecturaApplicationService lecturaApplicationService;

    public LecturaController(LecturaApplicationService lecturaApplicationService) {
        this.lecturaApplicationService = lecturaApplicationService;
    }

    @GetMapping
    @Operation(summary = "Listar lecturas recientes")
    public List<LecturaResponse> listar(
            @PathVariable UUID zonaId, @RequestParam(name = "limite", defaultValue = "50") int limite) {
        return lecturaApplicationService.listarPorZona(zonaId, limite).stream()
                .map(WebDtoMapper::toResponse)
                .toList();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar lectura")
    public ResponseEntity<LecturaResponse> registrar(
            @PathVariable UUID zonaId, @Valid @RequestBody RegistrarLecturaRequest request) {
        var lectura = lecturaApplicationService.registrar(zonaId, request.getTipo(), request.getValor());
        return ResponseEntity.status(HttpStatus.CREATED).body(WebDtoMapper.toResponse(lectura));
    }
}
