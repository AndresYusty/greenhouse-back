/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Spring Data — repositorio de lecturas.
 */
package com.invernadero.repository;

import com.invernadero.model.entity.LecturaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repositorio JPA para {@link LecturaEntity}. */
public interface LecturaJpaRepository extends JpaRepository<LecturaEntity, UUID> {

    List<LecturaEntity> findByZona_IdOrderByRegistradoEnDesc(UUID zonaId, Pageable pageable);

    void deleteByZona_Id(UUID zonaId);
}
