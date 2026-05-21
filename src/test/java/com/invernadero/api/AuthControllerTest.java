/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Pruebas WebMvc — estado de autenticación (OAuth2 opcional).
 */
package com.invernadero.api;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.invernadero.config.AppProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AuthController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppProperties appProperties;

    private AppProperties.Security security;

    @BeforeEach
    void setUp() {
        security = mock(AppProperties.Security.class);
        when(appProperties.getSecurity()).thenReturn(security);
    }

    @Test
    void sinOAuthDevuelveModoAbierto() throws Exception {
        when(security.isOauth2Enabled()).thenReturn(false);

        mockMvc.perform(get("/api/v1/auth/status").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authenticated").value(true))
                .andExpect(jsonPath("$.oauth2Enabled").value(false));
    }

    @Test
    void conOAuthSinSesionDevuelveLoginUrl() throws Exception {
        when(security.isOauth2Enabled()).thenReturn(true);

        mockMvc.perform(get("/api/v1/auth/status").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authenticated").value(false))
                .andExpect(jsonPath("$.oauth2Enabled").value(true))
                .andExpect(jsonPath("$.loginUrl").value("/oauth2/authorization/google"));
    }
}
