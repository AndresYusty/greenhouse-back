/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * API REST — zonas.
 */
package com.invernadero.api;

import com.invernadero.api.dto.CrearZonaRequest;
import com.invernadero.api.dto.ZonaResponse;
import com.invernadero.api.mapper.WebDtoMapper;
import com.invernadero.service.ZonaApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/zonas", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Zonas", description = "Gestión de zonas del invernadero")
public class ZonaController {

    private final ZonaApplicationService zonaApplicationService;

    public ZonaController(ZonaApplicationService zonaApplicationService) {
        this.zonaApplicationService = zonaApplicationService;
    }

    @GetMapping
    @Operation(summary = "Listar zonas")
    public List<ZonaResponse> listar() {
        return zonaApplicationService.listar().stream().map(WebDtoMapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener zona por id")
    public ZonaResponse obtener(@PathVariable UUID id) {
        return WebDtoMapper.toResponse(zonaApplicationService.obtener(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crear zona")
    public ResponseEntity<ZonaResponse> crear(@Valid @RequestBody CrearZonaRequest request) {
        var zona = zonaApplicationService.crear(request.getNombre(), request.getDescripcion());
        return ResponseEntity.status(HttpStatus.CREATED).body(WebDtoMapper.toResponse(zona));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar zona y sus lecturas")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        zonaApplicationService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
