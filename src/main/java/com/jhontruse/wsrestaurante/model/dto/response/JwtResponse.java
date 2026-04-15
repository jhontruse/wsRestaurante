package com.jhontruse.wsrestaurante.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Response que genera token")
public class JwtResponse {

    @Schema(description = "Token de acceso")
    private String accessToken;

    @Schema(description = "Tipo de autenticacion")
    private String authType;

    @Schema(description = "Token de refresh")
    private String refreshToken;

}
