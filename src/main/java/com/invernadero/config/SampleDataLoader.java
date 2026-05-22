/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Datos de ejemplo al arrancar (MySQL); solo si la tabla de zonas está vacía.
 */
package com.invernadero.config;

import com.invernadero.model.MetricaTipo;
import com.invernadero.model.entity.CultivoEntity;
import com.invernadero.model.entity.LecturaEntity;
import com.invernadero.model.entity.UmbralEntity;
import com.invernadero.model.entity.ZonaEntity;
import com.invernadero.repository.CultivoJpaRepository;
import com.invernadero.repository.LecturaJpaRepository;
import com.invernadero.repository.UmbralJpaRepository;
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
    private static final UUID CULTIVO_DEMO_ID = UUID.fromString("33333333-3333-4333-8333-333333333333");
    private static final UUID UMBRAL_DEMO_ID = UUID.fromString("44444444-4444-4444-8444-444444444444");

    private final ZonaJpaRepository zonaJpaRepository;
    private final LecturaJpaRepository lecturaJpaRepository;
    private final CultivoJpaRepository cultivoJpaRepository;
    private final UmbralJpaRepository umbralJpaRepository;

    public SampleDataLoader(
            ZonaJpaRepository zonaJpaRepository,
            LecturaJpaRepository lecturaJpaRepository,
            CultivoJpaRepository cultivoJpaRepository,
            UmbralJpaRepository umbralJpaRepository) {
        this.zonaJpaRepository = zonaJpaRepository;
        this.lecturaJpaRepository = lecturaJpaRepository;
        this.cultivoJpaRepository = cultivoJpaRepository;
        this.umbralJpaRepository = umbralJpaRepository;
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
        lectura.setZona(zona);
        lectura.setTipo(MetricaTipo.TEMPERATURA_C);
        lectura.setValor(BigDecimal.valueOf(22.5));
        lectura.setRegistradoEn(Instant.now());
        lecturaJpaRepository.save(lectura);

        CultivoEntity cultivo = new CultivoEntity();
        cultivo.setId(CULTIVO_DEMO_ID);
        cultivo.setZona(zona);
        cultivo.setNombre("Lechuga");
        cultivo.setVariedad("Romana");
        cultivo.setNotas("Ejemplo demo");
        Instant ahora = Instant.now();
        cultivo.setPlantadoEn(ahora);
        cultivo.setCreadoEn(ahora);
        cultivoJpaRepository.save(cultivo);

        UmbralEntity umbral = new UmbralEntity();
        umbral.setId(UMBRAL_DEMO_ID);
        umbral.setZona(zona);
        umbral.setTipo(MetricaTipo.TEMPERATURA_C);
        umbral.setValorMin(BigDecimal.valueOf(18));
        umbral.setValorMax(BigDecimal.valueOf(26));
        umbral.setCreadoEn(ahora);
        umbralJpaRepository.save(umbral);
    }
}
