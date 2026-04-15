package com.jhontruse.wsrestaurante.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "Request para crear un Rol con sus Menús asignados")
public class RolListMenuRequest {

    @Schema(description = "ID único de rol", example = "")
    private String id;

    // ── Datos del Rol ─────────────────────────────────────────
    @Schema(description = "Nombre del rol", example = "MOZO")
    @NotBlank(message = "El nombre del rol es obligatorio.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    private String nombre;

    @Schema(description = "Descripción del rol", example = "Rol para mozos del restaurante")
    @NotBlank(message = "La descripción del rol es obligatoria.")
    @NotNull(message = "El campo es obligatorio.")
    @Size(min = 3, max = 150, message = "La descripción debe tener entre 3 y 150 caracteres.")
    private String descripcion;

    @Schema(description = "Estado activo del rol", example = "true")
    @NotNull(message = "El campo activo es obligatorio.")
    private Boolean activo = true;

    @Schema(description = "Fecha registro del rol", example = "")
    private LocalDateTime fechaCreacion;

    // ── Lista de menús asignados al rol ───────────────────────
    @Schema(description = "Lista de IDs de menús asignados al rol")
    @NotEmpty(message = "Debe asignar al menos un menú al rol.")
    private List<String> menuIds;

}
