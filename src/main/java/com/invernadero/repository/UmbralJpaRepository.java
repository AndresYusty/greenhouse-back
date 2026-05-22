/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 */
package com.invernadero.repository;

import com.invernadero.model.MetricaTipo;
import com.invernadero.model.entity.UmbralEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UmbralJpaRepository extends JpaRepository<UmbralEntity, UUID> {

    List<UmbralEntity> findByZonaIdOrderByTipo(UUID zonaId);

    Optional<UmbralEntity> findByZonaIdAndTipo(UUID zonaId, MetricaTipo tipo);

    void deleteByZonaId(UUID zonaId);
}
