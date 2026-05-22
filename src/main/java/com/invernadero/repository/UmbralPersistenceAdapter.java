/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 */
package com.invernadero.repository;

import com.invernadero.model.MetricaTipo;
import com.invernadero.model.UmbralAmbiental;
import com.invernadero.repository.mapper.UmbralMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UmbralPersistenceAdapter implements UmbralPersistencePort {

    private final UmbralJpaRepository repository;

    public UmbralPersistenceAdapter(UmbralJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UmbralAmbiental guardar(UmbralAmbiental umbral) {
        return UmbralMapper.toDomain(repository.save(UmbralMapper.toEntity(umbral)));
    }

    @Override
    public List<UmbralAmbiental> listarPorZona(UUID zonaId) {
        return repository.findByZonaIdOrderByTipo(zonaId).stream()
                .map(UmbralMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<UmbralAmbiental> buscarPorZonaYTipo(UUID zonaId, MetricaTipo tipo) {
        return repository.findByZonaIdAndTipo(zonaId, tipo).map(UmbralMapper::toDomain);
    }

    @Override
    public void eliminarTodosPorZona(UUID zonaId) {
        repository.deleteByZonaId(zonaId);
    }
}
