/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Cultivos registrados por zona.
 */
package com.invernadero.api;

import com.invernadero.api.dto.CrearCultivoRequest;
import com.invernadero.api.dto.CultivoResponse;
import com.invernadero.api.mapper.WebDtoMapper;
import com.invernadero.service.CultivoApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/zonas/{zonaId}/cultivos", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Cultivos", description = "Cultivos asociados a una zona")
public class CultivoController {

    private final CultivoApplicationService cultivoApplicationService;

    public CultivoController(CultivoApplicationService cultivoApplicationService) {
        this.cultivoApplicationService = cultivoApplicationService;
    }

    @GetMapping
    @Operation(summary = "Listar cultivos")
    public List<CultivoResponse> listar(@PathVariable UUID zonaId) {
        return cultivoApplicationService.listarPorZona(zonaId).stream()
                .map(WebDtoMapper::toResponse)
                .toList();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar cultivo")
    public ResponseEntity<CultivoResponse> crear(
            @PathVariable UUID zonaId, @Valid @RequestBody CrearCultivoRequest request) {
        var c = cultivoApplicationService.registrar(
                zonaId, request.getNombre(), request.getVariedad(), request.getNotas(), request.getPlantadoEn());
        return ResponseEntity.ok(WebDtoMapper.toResponse(c));
    }
}
