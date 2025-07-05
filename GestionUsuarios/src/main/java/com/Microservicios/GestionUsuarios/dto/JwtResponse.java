package com.Microservicios.GestionUsuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "TokenResponse", description = "Respuesta con token JWT")
public class JwtResponse {

    @Schema(description = "Token JWT válido para autenticación", example = "eyJhbGciOiJIUzI1NiIsIn...")
    private String token;
}