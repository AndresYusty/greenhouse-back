/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Spring Data — repositorio de zonas.
 */
package com.invernadero.repository;

import com.invernadero.model.entity.ZonaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repositorio JPA para {@link ZonaEntity}. */
public interface ZonaJpaRepository extends JpaRepository<ZonaEntity, UUID> {}
