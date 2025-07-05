package com.Microservicios.GestionUsuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UsuarioResponseRol", description = "DTO con la información pública del usuario para cuando se busca por rol")
public class UsuarioResponseRol {

    @Schema(description = "ID del usuario", example = "1")
    private Long id;

    @Schema(description = "Nombre del usuario", example = "Ana")
    private String nombre;

    @Schema(description = "Apellido del usuario", example = "Gómez")
    private String apellido;

    @Schema(description = "Correo electrónico del usuario", example = "ana.gomez@demo.com")
    private String correo;

    @Schema(description = "Número de teléfono", example = "987654321")
    private String telefono;

    @Schema(description = "Estado del usuario", example = "true")
    private Boolean estado;

    
}