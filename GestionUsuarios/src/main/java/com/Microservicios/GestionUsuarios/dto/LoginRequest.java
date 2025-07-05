package com.Microservicios.GestionUsuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Credenciales Login", description = "DTO para autenticación de usuarios")
public class LoginRequest {

    @Schema(description = "Correo electrónico", example = "admin@demo.com")
    private String correo;

    @Schema(description = "Contraseña", example = "admin123")
    private String clave;
}