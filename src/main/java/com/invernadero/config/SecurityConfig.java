/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Seguridad OAuth2 (Google) y reglas de autorización HTTP.
 */
package com.invernadero.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Si {@code app.security.oauth2-enabled=true}, protege {@code /api/**} y habilita login Google.
 * En desarrollo por defecto las rutas quedan abiertas para poder probar sin credenciales OAuth.
 */
@Configuration
public class SecurityConfig {

    /**
     * Cadena de filtros principal.
     *
     * @param http builder de seguridad
     * @param appProperties interruptor OAuth2
     * @return cadena configurada
     */
    @Bean
    @Order(1)
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AppProperties appProperties,
            AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler)
            throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.cors(Customizer.withDefaults());

        if (!appProperties.getSecurity().isOauth2Enabled()) {
            http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
            return http.build();
        }

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/actuator/**")
                .permitAll()
                .requestMatchers("/error")
                .permitAll()
                .requestMatchers("/oauth2/**", "/login/oauth2/**")
                .permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/api/**")
                .permitAll()
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                .requestMatchers("/api/**")
                .authenticated()
                .anyRequest()
                .permitAll());
        http.oauth2Login(oauth2 -> oauth2.successHandler(oauth2AuthenticationSuccessHandler));
        http.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl(appProperties.getOauth2().getPostLoginRedirect()));
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
        return http.build();
    }

    /**
     * CORS para preflight bajo Spring Security (complementa {@link WebConfig}).
     *
     * @param appProperties orígenes permitidos
     * @return fuente CORS
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource(AppProperties appProperties) {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOriginPatterns(Arrays.asList(appProperties.getCors().originArray()));
        cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        cfg.setAllowCredentials(true);
        cfg.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", cfg);
        return source;
    }
}
