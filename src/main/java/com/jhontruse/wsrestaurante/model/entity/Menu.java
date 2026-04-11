package com.jhontruse.wsrestaurante.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un menú en el sistema")
@Table("MENU")
public class Menu {

    @Id
    @Column("ID")
    @Schema(description = "ID único de menu", example = "")
    private String id;

    @Schema(description = "Nombre del menú", example = "")
    @Column("NOMBRE")
    @Size(max = 120, min = 3)
    @NotNull(message = "El campo es obligatorio.")
    private String nombre;

    @Schema(description = "Ruta del menú", example = "")
    @Column("RUTA")
    @Size(max = 150, min = 3)
    @NotNull(message = "El campo es obligatorio.")
    private String ruta;

    @Schema(description = "Icono del menú", example = "")
    @Column("ICONO")
    @Size(max = 80, min = 3)
    @NotNull(message = "El campo es obligatorio.")
    private String icono;

    @Schema(description = "Orden del menú", example = "")
    @Column("ORDEN")
    @NotNull(message = "El campo es obligatorio.")
    @Positive(message = "El campo debe ser positivo.")
    private Integer orden;

    @Schema(description = "Activo del menú", example = "")
    @Column("ACTIVO")
    @NotNull(message = "El campo es obligatorio.")
    private Boolean activo = true;

}
