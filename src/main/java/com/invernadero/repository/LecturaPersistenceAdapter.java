/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Implementación — persistencia de lecturas (Spring Data).
 */
package com.invernadero.repository;

import com.invernadero.model.LecturaAmbiental;
import com.invernadero.repository.mapper.LecturaMapper;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class LecturaPersistenceAdapter implements LecturaPersistencePort {

    private final LecturaJpaRepository repository;

    public LecturaPersistenceAdapter(LecturaJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public LecturaAmbiental guardar(LecturaAmbiental lectura) {
        var saved = repository.save(LecturaMapper.toEntity(lectura));
        return LecturaMapper.toDomain(saved);
    }

    @Override
    public List<LecturaAmbiental> listarPorZona(UUID zonaId, int limite) {
        int size = Math.min(Math.max(limite, 1), 500);
        return repository.findByZonaIdOrderByRegistradoEnDesc(zonaId, PageRequest.of(0, size)).stream()
                .map(LecturaMapper::toDomain)
                .toList();
    }

    @Override
    public void eliminarTodasPorZona(UUID zonaId) {
        repository.deleteByZonaId(zonaId);
    }
}
