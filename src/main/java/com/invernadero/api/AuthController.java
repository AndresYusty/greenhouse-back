/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * API REST — estado de sesión OAuth2 para el SPA.
 */
package com.invernadero.api;

import com.invernadero.api.dto.AuthStatusResponse;
import com.invernadero.config.AppProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Auth", description = "Estado de sesión OAuth2")
public class AuthController {

    /** Ruta estándar Spring Security OAuth2 Client para Google. */
    public static final String OAUTH2_GOOGLE_START_PATH = "/oauth2/authorization/google";

    private final AppProperties appProperties;

    public AuthController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping("/status")
    @Operation(summary = "Estado de autenticación", description = "Indica si OAuth2 está activo y si la sesión actual está autenticada.")
    public ResponseEntity<AuthStatusResponse> status(Authentication authentication) {
        if (!appProperties.getSecurity().isOauth2Enabled()) {
            return ResponseEntity.ok(AuthStatusResponse.openMode());
        }
        boolean authenticated = isLoggedIn(authentication);
        String email = null;
        String name = null;
        if (authenticated && authentication != null && authentication.getPrincipal() instanceof OAuth2User oauth2User) {
            email = oauth2User.getAttribute("email");
            name = oauth2User.getAttribute("name");
        }
        String loginUrl = authenticated ? null : OAUTH2_GOOGLE_START_PATH;
        return ResponseEntity.ok(new AuthStatusResponse(authenticated, true, loginUrl, email, name));
    }

    private static boolean isLoggedIn(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        Object principal = authentication.getPrincipal();
        return principal != null && !"anonymousUser".equals(principal.toString());
    }
}
