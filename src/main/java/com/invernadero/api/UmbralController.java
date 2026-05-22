/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Rangos recomendados por zona y métrica.
 */
package com.invernadero.api;

import com.invernadero.api.dto.DefinirUmbralRequest;
import com.invernadero.api.dto.UmbralResponse;
import com.invernadero.api.mapper.WebDtoMapper;
import com.invernadero.service.UmbralApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/zonas/{zonaId}/umbrales", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Umbrales", description = "Referencias de rango ambiental por zona")
public class UmbralController {

    private final UmbralApplicationService umbralApplicationService;

    public UmbralController(UmbralApplicationService umbralApplicationService) {
        this.umbralApplicationService = umbralApplicationService;
    }

    @GetMapping
    @Operation(summary = "Listar umbrales de la zona")
    public List<UmbralResponse> listar(@PathVariable UUID zonaId) {
        return umbralApplicationService.listarPorZona(zonaId).stream()
                .map(WebDtoMapper::toResponse)
                .toList();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Definir o actualizar umbral por tipo de métrica")
    public ResponseEntity<UmbralResponse> definir(
            @PathVariable UUID zonaId, @Valid @RequestBody DefinirUmbralRequest request) {
        var u = umbralApplicationService.definir(
                zonaId, request.getTipo(), request.getValorMin(), request.getValorMax());
        return ResponseEntity.ok(WebDtoMapper.toResponse(u));
    }
}
