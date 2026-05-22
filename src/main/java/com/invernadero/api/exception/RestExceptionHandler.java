/*
 * Copyright (c) 2026 — Proyecto académico Invernadero.
 * Manejo centralizado de errores REST e i18n.
 */
package com.invernadero.api.exception;

import com.invernadero.model.exception.RecursoNoEncontradoException;
import jakarta.validation.ConstraintViolationException;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Traduce excepciones de dominio y validación a respuestas HTTP. */
@RestControllerAdvice
public class RestExceptionHandler {

    private final MessageSource messageSource;

    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorBody> notFound(RecursoNoEncontradoException ex, Locale locale) {
        String msg = messageSource.getMessage("error.not_found", null, locale);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorBody(msg, ex.getMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorBody> validation(Exception ex, Locale locale) {
        String msg = messageSource.getMessage("error.validation", null, locale);
        String detail = ex instanceof MethodArgumentNotValidException manv
                ? manv.getBindingResult().getFieldErrors().stream()
                        .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                        .collect(Collectors.joining("; "))
                : ex.getMessage();
        return ResponseEntity.badRequest().body(new ErrorBody(msg, detail));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorBody> illegalArgument(IllegalArgumentException ex, Locale locale) {
        String msg = messageSource.getMessage("error.validation", null, locale);
        return ResponseEntity.badRequest().body(new ErrorBody(msg, ex.getMessage()));
    }

    /** Cuerpo JSON simple para errores. */
    public record ErrorBody(String message, String detail) {}
}
