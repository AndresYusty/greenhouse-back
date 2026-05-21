/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Pruebas WebMvc — controlador de zonas (JUnit).
 */
package com.invernadero.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.invernadero.api.exception.RestExceptionHandler;
import com.invernadero.model.Zona;
import com.invernadero.service.ZonaApplicationService;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(
        controllers = ZonaController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
@Import(RestExceptionHandler.class)
@AutoConfigureMockMvc(addFilters = false)
class ZonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZonaApplicationService zonaApplicationService;

    @Test
    void listarDevuelveJson() throws Exception {
        UUID id = UUID.randomUUID();
        when(zonaApplicationService.listar())
                .thenReturn(List.of(new Zona(id, "X", "Y", Instant.parse("2026-01-01T00:00:00Z"))));

        mockMvc.perform(get("/api/v1/zonas").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("X"));
    }
}
