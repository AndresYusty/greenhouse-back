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

    List<UmbralEntity> findByZona_IdOrderByTipo(UUID zonaId);

    Optional<UmbralEntity> findByZona_IdAndTipo(UUID zonaId, MetricaTipo tipo);

    void deleteByZona_Id(UUID zonaId);
}
