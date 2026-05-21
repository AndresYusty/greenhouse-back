/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Datos de ejemplo al arrancar (MySQL); solo si la tabla de zonas está vacía.
 */
package com.invernadero.config;

import com.invernadero.model.MetricaTipo;
import com.invernadero.model.entity.LecturaEntity;
import com.invernadero.model.entity.ZonaEntity;
import com.invernadero.repository.LecturaJpaRepository;
import com.invernadero.repository.ZonaJpaRepository;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(100)
@ConditionalOnProperty(prefix = "app.sample-data", name = "enabled", havingValue = "true", matchIfMissing = true)
public class SampleDataLoader implements ApplicationRunner {

    private static final UUID ZONA_DEMO_ID = UUID.fromString("11111111-1111-4111-8111-111111111111");
    private static final UUID LECTURA_DEMO_ID = UUID.fromString("22222222-2222-4222-8222-222222222222");

    private final ZonaJpaRepository zonaJpaRepository;
    private final LecturaJpaRepository lecturaJpaRepository;

    public SampleDataLoader(ZonaJpaRepository zonaJpaRepository, LecturaJpaRepository lecturaJpaRepository) {
        this.zonaJpaRepository = zonaJpaRepository;
        this.lecturaJpaRepository = lecturaJpaRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (zonaJpaRepository.count() > 0) {
            return;
        }
        ZonaEntity zona = new ZonaEntity();
        zona.setId(ZONA_DEMO_ID);
        zona.setNombre("Zona norte");
        zona.setDescripcion("Cultivo demo — automatización de riego");
        zona.setCreadoEn(Instant.now());
        zonaJpaRepository.save(zona);

        LecturaEntity lectura = new LecturaEntity();
        lectura.setId(LECTURA_DEMO_ID);
        lectura.setZonaId(ZONA_DEMO_ID);
        lectura.setTipo(MetricaTipo.TEMPERATURA_C);
        lectura.setValor(BigDecimal.valueOf(22.5));
        lectura.setRegistradoEn(Instant.now());
        lecturaJpaRepository.save(lectura);
    }
}
