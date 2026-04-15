package com.jhontruse.wsrestaurante.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(description = "Response con el Rol y sus Menús asignados")
public class RolListMenuResponse {

    // ── Datos del Rol ─────────────────────────────────────────
    @Schema(description = "ID único del rol")
    private String id;

    @Schema(description = "Nombre del rol")
    private String nombre;

    @Schema(description = "Descripción del rol")
    private String descripcion;

    @Schema(description = "Estado activo del rol")
    private Boolean activo;

    @Schema(description = "Fecha de registro")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacion;

    // ── Lista de menús asignados ──────────────────────────────
    @Schema(description = "Menús a los que tiene acceso el rol")
    private List<MenuItemResponse> menus;

    // ── DTO interno para cada menú ────────────────────────────
    @Data
    @Builder
    @Schema(description = "Detalle de un menú asignado al rol")
    public static class MenuItemResponse {

        @Schema(description = "ID del menú")
        private String id;

        @Schema(description = "Nombre del menú")
        private String nombre;

        @Schema(description = "Ruta del menú")
        private String ruta;

        @Schema(description = "Ícono del menú")
        private String icono;

        @Schema(description = "Orden de visualización")
        private Integer orden;

        @Schema(description = "Estado activo del menú")
        private Boolean activo;
    }

}
