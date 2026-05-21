/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Implementación — persistencia de zonas (Spring Data).
 */
package com.invernadero.repository;

import com.invernadero.model.Zona;
import com.invernadero.repository.mapper.ZonaMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ZonaPersistenceAdapter implements ZonaPersistencePort {

    private final ZonaJpaRepository repository;

    public ZonaPersistenceAdapter(ZonaJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Zona guardar(Zona zona) {
        var saved = repository.save(ZonaMapper.toEntity(zona));
        return ZonaMapper.toDomain(saved);
    }

    @Override
    public List<Zona> listarTodas() {
        return repository.findAll().stream().map(ZonaMapper::toDomain).toList();
    }

    @Override
    public Optional<Zona> buscarPorId(UUID id) {
        return repository.findById(id).map(ZonaMapper::toDomain);
    }

    @Override
    public void eliminar(UUID id) {
        repository.deleteById(id);
    }
}
