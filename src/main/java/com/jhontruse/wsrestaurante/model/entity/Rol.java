package com.jhontruse.wsrestaurante.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un rol en el sistema")
@Table("ROL")
public class Rol {

    @Id
    @Column("ID")
    @Schema(description = "ID único de rol", example = "")
    private String id;

    @Schema(description = "Nombre del rol", example = "")
    @Column("NOMBRE")
    @Size(max = 50, min = 3)
    @NotNull(message = "El campo es obligatorio.")
    private String nombre;

    @Schema(description = "Descripcion del rol", example = "")
    @Column("DESCRIPCION")
    @Size(max = 150, min = 3)
    @NotNull(message = "El campo es obligatorio.")
    private String descripcion;

    @Schema(description = "Activo del rol", example = "")
    @Column("ACTIVO")
    @NotNull(message = "El campo es obligatorio.")
    private Boolean activo = true;

    @Schema(description = "Fecha registro del rol", example = "")
    @Column("FECHA_CREACION")
    private LocalDateTime fechaCreacion;

}
