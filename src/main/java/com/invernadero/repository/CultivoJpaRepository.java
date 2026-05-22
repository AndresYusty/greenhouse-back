/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 */
package com.invernadero.repository;

import com.invernadero.model.entity.CultivoEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CultivoJpaRepository extends JpaRepository<CultivoEntity, UUID> {

    List<CultivoEntity> findByZona_IdOrderByPlantadoEnDesc(UUID zonaId);

    void deleteByZona_Id(UUID zonaId);
}
