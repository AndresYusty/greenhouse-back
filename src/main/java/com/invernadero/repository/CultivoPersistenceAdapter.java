/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 */
package com.invernadero.repository;

import com.invernadero.model.Cultivo;
import com.invernadero.repository.mapper.CultivoMapper;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CultivoPersistenceAdapter implements CultivoPersistencePort {

    private final CultivoJpaRepository repository;

    public CultivoPersistenceAdapter(CultivoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cultivo guardar(Cultivo cultivo) {
        return CultivoMapper.toDomain(repository.save(CultivoMapper.toEntity(cultivo)));
    }

    @Override
    public List<Cultivo> listarPorZona(UUID zonaId) {
        return repository.findByZonaIdOrderByPlantadoEnDesc(zonaId).stream()
                .map(CultivoMapper::toDomain)
                .toList();
    }

    @Override
    public void eliminarTodosPorZona(UUID zonaId) {
        repository.deleteByZonaId(zonaId);
    }
}
