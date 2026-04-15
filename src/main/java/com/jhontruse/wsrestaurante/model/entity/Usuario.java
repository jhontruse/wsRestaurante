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
@Schema(description = "Entidad que representa un usuario en el sistema")
@Table("USUARIO")
public class Usuario {

    @Schema(description = "ID único de usuario", example = "")
    @Id
    @Column("ID")
    private String id;

    @Schema(description = "ID de persona", example = "")
    @Column("PERSONA_ID")
    @NotNull(message = "El campo es obligatorio.")
    private String personaId;

    @Schema(description = "Nombre del usuario", example = "")
    @Column("USERNAME")
    @Size(max = 100, min = 3)
    @NotNull(message = "El campo es obligatorio.")
    private String username;

    @Schema(description = "Password del usuario", example = "")
    @Column("PASSWORD")
    @Size(max = 800, min = 3)
    @NotNull(message = "El campo es obligatorio.")
    private String password;

    @Schema(description = "ID del rol", example = "")
    @Column("ROL_ID")
    @NotNull(message = "El campo es obligatorio.")
    private String rolId;

    @Schema(description = "Activo del usuario", example = "")
    @Column("ACTIVO")
    @NotNull(message = "El campo es obligatorio.")
    private Boolean activo; // true = activo, false = deshabilitado

    @Schema(description = "Fecha de ultimo login del usuario", example = "")
    @Column("FECHA_ULTIMO_LOGIN")
    private LocalDateTime fechaUltimoLogin;

    @Schema(description = "Fecha de creacion del usuario", example = "")
    @Column("FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @Schema(description = "Fecha de creacion del usuario", example = "")
    @Column("FECHA_ACTU")
    private LocalDateTime fechaActu;

    @Schema(description = "Bloqueo del usuario", example = "")
    @Column("MCA_BLOQUEADO")
    private String mcaBloqueado; // S = bloqueado, N = no bloqueado

    @Schema(description = "Fecha de cuenta expirada del usuario", example = "")
    @Column("FECHA_USUARIO_EXPIRADO")
    private LocalDateTime fechaUsuarioExpirado; // fecha de expiración de cuenta

    @Schema(description = "Fecha de password expirada del usuario", example = "")
    @Column("FECHA_PASSWORD_EXPIRADO")
    private LocalDateTime fechaPasswordExpirado; // fecha de expiración de credenciales

    @Schema(description = "Intento fallido de login del usuario", example = "")
    @Column("NUM_FALLIDO_LOGIN")
    @NotNull(message = "El campo es obligatorio.")
    private Integer numFallidoLogin; // para políticas de bloqueo

    @Schema(description = "Fecha que se bloqueo usuario", example = "")
    @Column("FECHA_BLOQUEO")
    private LocalDateTime fechaBloqueo; // fecha y hora en que se bloqueó la cuenta

}
