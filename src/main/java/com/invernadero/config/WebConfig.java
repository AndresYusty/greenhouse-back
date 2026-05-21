/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Web MVC — idioma (i18n). CORS para {@code /api/**} lo define {@link SecurityConfig}.
 */
package com.invernadero.config;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * Resolución de locale vía cabecera Accept-Language y parámetro {@code lang}.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Locale por defecto español; se puede cambiar con {@code ?lang=en}.
     *
     * @return resolver de idioma
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("es"));
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    }
}
