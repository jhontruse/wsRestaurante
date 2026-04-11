package com.jhontruse.wsrestaurante.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Entidad que representa un response de excepciones")
public class ApiErrorResponse {

    @Schema(
            description = "Fecha y hora en que ocurrió el error (ISO 8601)",
            example = "2024-11-15T10:30:45.123"
    )
    LocalDateTime timestamp;

    @Schema(
            description = "Código HTTP del error",
            example = "401"
    )
    Integer status;

    @Schema(
            description = "Código estable de error para frontend y logs",
            example = "JWT_EXPIRED"
    )
    String code; // Código estable para frontend y logs (ej.: AUTH_LOCKED, JWT_EXPIRED)

    @Schema(
            description = "Mensaje legible para el usuario",
            example = "Su sesión ha expirado. Por favor, vuelva a iniciar sesión."
    )
    String message; // Mensaje legible para el usuario

    @Schema(
            description = "Causa técnica del error, útil para debugging",
            example = "io.jsonwebtoken.ExpiredJwtException"
    )
    String detail; // Clase/causa técnica opcional

    @Schema(
            description = "URI del request que originó el error",
            example = "/api/v1/siniestros/123"
    )
    String path; // request.getRequestURI()

    @Schema(
            description = "ID de correlación para trazabilidad distribuida",
            example = "a3f1b2c4-d5e6-7890-abcd-ef1234567890"
    )
    String correlationId; // si usas MDC o header X-Correlation-Id

    @Schema(
            description = "Lista de errores de validación de campos",
            example = "[\"El campo 'email' no tiene un formato válido\", \"El campo 'status' es obligatorio\"]"
    )
    List<String> errors; // lista de errores de validación, etc.

}
