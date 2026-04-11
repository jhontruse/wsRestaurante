package com.jhontruse.wsrestaurante.model.mapper;

import com.jhontruse.wsrestaurante.model.dto.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class ApiErrorResponseMapper {

    private ApiErrorResponseMapper() {
        /* This utility class should not be instantiated */
    }

    public static ApiErrorResponse toResponse(Integer status, String code, String message,
                                              String detail, HttpServletRequest req, String correlationId,
                                              List<String> errors) {
        log.error("status: {}", status);
        log.error("code: {}", code);
        log.error("message: {}", message);
        log.error("detail: {}", detail);
        log.error("req: {}", req);
        log.error("correlationId: {}", correlationId);
        log.error("errors: {}", errors);
        return ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .code(code)
                .message(message)
                .detail(detail)
                .path(req != null ? req.getRequestURI() : null)
                .correlationId(correlationId)
                .errors(errors)
                .build();
    }

}
