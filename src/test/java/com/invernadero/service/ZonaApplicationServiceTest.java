/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Pruebas unitarias — servicio de zonas (JUnit).
 */
package com.invernadero.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.invernadero.model.Zona;
import com.invernadero.model.exception.RecursoNoEncontradoException;
import com.invernadero.repository.LecturaPersistencePort;
import com.invernadero.repository.ZonaPersistencePort;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ZonaApplicationServiceTest {

    @Mock
    private ZonaPersistencePort zonaPersistencePort;

    @Mock
    private LecturaPersistencePort lecturaPersistencePort;

    @InjectMocks
    private ZonaApplicationService service;

    @Test
    void crearPersisteZona() {
        UUID id = UUID.randomUUID();
        when(zonaPersistencePort.guardar(any(Zona.class))).thenAnswer(inv -> inv.getArgument(0));

        Zona result = service.crear("Lab", "Desc");

        assertThat(result.getNombre()).isEqualTo("Lab");
        verify(zonaPersistencePort).guardar(any(Zona.class));
    }

    @Test
    void obtenerLanzaSiNoExiste() {
        UUID id = UUID.randomUUID();
        when(zonaPersistencePort.buscarPorId(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.obtener(id)).isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void listarDelegaEnPuerto() {
        when(zonaPersistencePort.listarTodas()).thenReturn(List.of());

        assertThat(service.listar()).isEmpty();
    }

    @Test
    void eliminarQuitaLecturasYZona() {
        UUID id = UUID.randomUUID();
        Zona z = new Zona(id, "N", "", Instant.now());
        when(zonaPersistencePort.buscarPorId(id)).thenReturn(Optional.of(z));

        service.eliminar(id);

        verify(lecturaPersistencePort).eliminarTodasPorZona(id);
        verify(zonaPersistencePort).eliminar(id);
    }

    @Test
    void eliminarSiNoExisteNoTocaLecturas() {
        UUID id = UUID.randomUUID();
        when(zonaPersistencePort.buscarPorId(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.eliminar(id)).isInstanceOf(RecursoNoEncontradoException.class);

        verifyNoInteractions(lecturaPersistencePort);
    }
}
