package com.jhontruse.wsrestaurante.exception;

import com.jhontruse.wsrestaurante.model.dto.response.ApiErrorResponse;
import com.jhontruse.wsrestaurante.model.mapper.ApiErrorResponseMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.security.auth.login.AccountExpiredException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final HttpServletRequest request;

    private String cid() {
        return request.getHeader("X-Correlation-Id");
    }

    // ==== Autenticación (login) ====
    /*@ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError badCredentials(BadCredentialsException ex) {
        log.error("********************************");
        log.error("********************************");
        log.error("GlobalAuthExceptionHandler - badCredentials");
        log.error("********************************");
        log.error("********************************");
        return ApiErrorMapper.of(401, "AUTH_BAD_CREDENTIALS",
                "Usuario o contraseña incorrectos.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }*/

        /*@ExceptionHandler(LockedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError locked(LockedException ex) {
        log.error("********************************");
        log.error("********************************");
        log.error("GlobalAuthExceptionHandler - locked");
        log.error("********************************");
        log.error("********************************");
        return ApiErrorMapper.of(401, "AUTH_LOCKED",
                "Tu cuenta está bloqueada. Contacta al administrador.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }*/

        /*@ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError disabled(DisabledException ex) {
        log.error("********************************");
        log.error("********************************");
        log.error("GlobalAuthExceptionHandler - disabled");
        log.error("********************************");
        log.error("********************************");
        return ApiErrorMapper.of(401, "AUTH_DISABLED",
                "Tu cuenta está deshabilitada.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }*/

        /*@ExceptionHandler(CredentialsExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError credentialsExpired(CredentialsExpiredException ex) {
        log.error("********************************");
        log.error("********************************");
        log.error("GlobalAuthExceptionHandler - credentialsExpired");
        log.error("********************************");
        log.error("********************************");
        return ApiErrorMapper.of(401, "AUTH_CREDENTIALS_EXPIRED",
                "Tu contraseña ha expirado. Debes actualizarla.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }*/

    @ExceptionHandler(AccountExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse accountExpired(AccountExpiredException ex) {
        log.warn("GlobalAuthExceptionHandler - accountExpired");
        return ApiErrorResponseMapper.toResponse(401, "AUTH_ACCOUNT_EXPIRED", "Tu cuenta ha expirado.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }

    /**
     * Se dispara cuando el usuario está autenticado pero no tiene permiso.
     * Ejemplo: un MOZO intenta acceder a /caja o /usuarios.
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrorResponse handleForbidden(AccessDeniedException ex) {
        log.warn("GlobalAuthExceptionHandler - handleForbidden");
        log.warn("CID={} | Acceso denegado | path={} | error={}", cid(), request.getRequestURI(), ex.getMessage());

        return ApiErrorResponseMapper.toResponse(
                403,
                "FORBIDDEN",
                "No tienes permisos para acceder a este recurso.",
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    // ==== JWT (filtros/servicios que validan token) ====
    /*@ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError jwtExpired(ExpiredJwtException ex) {
        log.error("********************************");
        log.error("********************************");
        log.error("GlobalAuthExceptionHandler - jwtExpired");
        log.error("********************************");
        log.error("********************************");
        return ApiErrorMapper.toResponse(401, "JWT_EXPIRED",
                "Tu token ha expirado.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }*/
    
        /*@ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError jwtInvalid(JwtException ex) {
        log.error("********************************");
        log.error("********************************");
        log.error("GlobalAuthExceptionHandler - jwtInvalid");
        log.error("********************************");
        log.error("********************************");
        return ApiErrorMapper.toResponse(401, "JWT_INVALID",
                "Token inválido.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }*/

    /**
     * Se dispara cuando un @RequestBody tiene campos con @Valid que fallan.
     * Ejemplo: campo requerido vacío, formato incorrecto, longitud inválida.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse validation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .toList();
        log.warn("GlobalAuthExceptionHandler - validation");
        log.warn("CID={} | Validación fallida | campos={}", cid(), errors);

        return ApiErrorResponseMapper.toResponse(
                400,
                "VALIDATION_ERROR",
                "Los datos enviados no son válidos. Revisa los campos indicados.",
                ex.getClass().getSimpleName(),
                request,
                cid(),
                errors
        );
    }

    /**
     * Se dispara cuando el body del request no puede ser leído o está malformado.
     * Ejemplo: JSON inválido, campo con tipo incorrecto (enviar texto en campo numérico).
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleNotReadable(HttpMessageNotReadableException ex) {
        log.warn("GlobalAuthExceptionHandler - handleNotReadable");
        log.warn("CID={} | Body del request no legible | error={}", cid(), ex.getMessage());

        return ApiErrorResponseMapper.toResponse(
                400,
                "INVALID_BODY",
                "El cuerpo del request no es válido o está malformado. Verifica el JSON enviado.",
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    /**
     * Se dispara cuando falta un @RequestParam requerido en la URL.
     * Ejemplo: GET /menus?page=0 si 'size' es requerido y no se envía.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleMissingParam(MissingServletRequestParameterException ex) {
        log.warn("GlobalAuthExceptionHandler - handleMissingParam");
        log.warn("CID={} | Parámetro requerido faltante | param={}", cid(), ex.getParameterName());

        return ApiErrorResponseMapper.toResponse(
                400,
                "MISSING_PARAMETER",
                "El parámetro requerido '" + ex.getParameterName() + "' no fue enviado.",
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    /**
     * Se dispara cuando un parámetro tiene tipo incorrecto.
     * Ejemplo: GET /menus/{id} donde id debe ser String pero se envía un formato inválido.
     * O GET /ordenes?page=abc cuando page debe ser Integer.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String mensaje = String.format(
                "El parámetro '%s' recibió el valor '%s' pero se esperaba tipo %s.",
                ex.getName(),
                ex.getValue(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido"
        );
        log.warn("GlobalAuthExceptionHandler - handleTypeMismatch");
        log.warn("CID={} | Tipo de parámetro incorrecto | {}", cid(), mensaje);

        return ApiErrorResponseMapper.toResponse(
                400,
                "TYPE_MISMATCH",
                mensaje,
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    /**
     * Se dispara cuando el usuario no está autenticado o el token es inválido.
     * Ejemplo: token JWT vencido, token malformado, sin Authorization header.
     */
    /*
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse authGeneric(AuthenticationException ex) {
        log.warn("GlobalAuthExceptionHandler - authGeneric");
        log.warn("CID={} | Error de autenticación | error={}", cid(), ex.getMessage());

        return ApiErrorResponseMapper.toResponse(
                401,
                "UNAUTHORIZED",
                "No autenticado. Verifica tu token o credenciales e intenta nuevamente.",
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }*/

    /**
     * Se dispara cuando se intenta insertar un registro con clave duplicada.
     * Ejemplo: crear un usuario con un username que ya existe (UNIQUE constraint).
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse duplicateKey(DuplicateKeyException ex) {
        log.warn("GlobalAuthExceptionHandler - duplicateKey");
        log.warn("CID={} | Clave duplicada en BD | error={}", cid(), ex.getMessage());

        return ApiErrorResponseMapper.toResponse(
                409,
                "DUPLICATE_KEY",
                "Ya existe un registro con los mismos datos únicos. Verifica los campos como username, documento, etc.",
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    /**
     * Se dispara cuando hay un conflicto de estado en la lógica del negocio.
     * Ejemplo: intentar enviar a cocina una orden que ya está CERRADA,
     * o agregar ítems a una orden PAGADA.
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handleIllegalState(IllegalStateException ex) {
        log.warn("GlobalAuthExceptionHandler - handleIllegalState");
        log.warn("CID={} | Estado inválido | mensaje={}", cid(), ex.getMessage());

        return ApiErrorResponseMapper.toResponse(
                409,
                "INVALID_STATE",
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    /**
     * Se dispara cuando el Content-Type del request no es soportado.
     * Ejemplo: enviar XML cuando el endpoint solo acepta application/json.
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ApiErrorResponse handleMediaType(HttpMediaTypeNotSupportedException ex) {
        log.warn("GlobalAuthExceptionHandler - handleMediaType");
        log.warn("CID={} | Media type no soportado | tipo={}", cid(), ex.getContentType());

        return ApiErrorResponseMapper.toResponse(
                415,
                "UNSUPPORTED_MEDIA_TYPE",
                "El tipo de contenido '" + ex.getContentType() + "' no es soportado. Usa application/json.",
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    /**
     * Se dispara cuando hay violación de integridad referencial en BD.
     * Ejemplo: eliminar un ROL que tiene USUARIOS asociados (FK violation),
     * o insertar un USUARIO con un ROL_ID que no existe.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse dataIntegrityViolation(DataIntegrityViolationException ex) {
        log.warn("GlobalAuthExceptionHandler - dataIntegrityViolation");
        log.warn("CID={} | Violación de integridad en BD | error={}", cid(), ex.getMessage());

        return ApiErrorResponseMapper.toResponse(
                422,
                "DATA_INTEGRITY_VIOLATION",
                "No se puede completar la operación porque viola restricciones de integridad. " +
                        "Puede que el registro tenga dependencias o referencias inválidas.",
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    /**
     * Se dispara cuando la consulta a Oracle excede el tiempo límite.
     * Ejemplo: query sin índice sobre tabla grande, BD sobrecargada.
     */
    @ExceptionHandler(QueryTimeoutException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ApiErrorResponse queryTimeout(QueryTimeoutException ex) {
        log.error("GlobalAuthExceptionHandler - queryTimeout");
        log.error("CID={} | Timeout en consulta BD | path={}", cid(), request.getRequestURI());

        return ApiErrorResponseMapper.toResponse(
                503,
                "DB_TIMEOUT",
                "La base de datos tardó demasiado en responder. Intenta nuevamente en unos momentos.",
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    /**
     * Se dispara cuando no se encuentra un registro en la base de datos.
     * Ejemplo: buscar un Menu por ID que no existe.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFound(ResourceNotFoundException ex) {
        log.warn("GlobalExceptionHandler - handleNotFound");
        log.warn("CID={} | Recurso no encontrado | mensaje={}", cid(), ex.getMessage());

        return ApiErrorResponseMapper.toResponse(
                404,
                "NOT_FOUND",
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    /**
     * Se dispara cuando no se encuentra un error en base de datos de negocio.
     * Ejemplo:
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse businessException(BusinessException ex) {
        log.error("GlobalExceptionHandler - businessException");
        log.error("CID={} | Error de negocio | mensaje={}", cid(), ex.getMessage());
        return ApiErrorResponseMapper.toResponse(
                ex.getStatus(),
                ex.getCode(),
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    /**
     * Se dispara cuando la URL solicitada no existe en la aplicación.
     * Ejemplo: llamar a /api/menuus en lugar de /api/menus.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNoResourceFound(NoResourceFoundException ex) {
        log.warn("GlobalExceptionHandler - handleNoResourceFound");
        log.warn("CID={} | Endpoint no encontrado | path={}", cid(), request.getRequestURI());

        return ApiErrorResponseMapper.toResponse(
                404,
                "ENDPOINT_NOT_FOUND",
                "El endpoint '" + request.getRequestURI() + "' no existe. Verifica la URL.",
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    /**
     * Se dispara cuando hay un error genérico de acceso a datos en Oracle.
     * Ejemplo: conexión perdida con la BD, error de SQL mal formado.
     * IMPORTANTE: va antes del handler genérico de Exception.
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleDataAccess(DataAccessException ex) {
        log.error("GlobalExceptionHandler - handleDataAccess");
        log.error("CID={} | Error de acceso a BD | path={} | error={}",
                cid(), request.getRequestURI(), ex.getMessage(), ex);

        return ApiErrorResponseMapper.toResponse(
                500,
                "DB_ERROR",
                "Ocurrió un error al acceder a la base de datos. Contacta al administrador.",
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

    /**
     * Captura cualquier excepción no controlada por los handlers anteriores.
     * SIEMPRE debe ir al final como fallback.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse exception(Exception ex) {
        log.error("GlobalExceptionHandler - exception");
        log.error("CID={} | Error inesperado | path={} | error={}",
                cid(), request.getRequestURI(), ex.getMessage(), ex);

        return ApiErrorResponseMapper.toResponse(
                500,
                "INTERNAL_ERROR",
                "Ocurrió un error inesperado. Contacta al administrador indicando el CID.",
                ex.getClass().getSimpleName(),
                request,
                cid(),
                null
        );
    }

}
