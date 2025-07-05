package com.Microservicios.GestionUsuarios.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(name = "CrearUsuarioRequest", description = "DTO para crear o actualizar un usuario")
public class UsuarioRequest {

    @Schema(description = "Nombre del usuario", example = "Ana")
    private String nombre;

    @Schema(description = "Apellido del usuario", example = "Gómez")
    private String apellido;

    @Schema(description = "Correo electrónico único", example = "ana.gomez@demo.com")
    private String correo;

    @Schema(description = "Contraseña del usuario", example = "123456")
    private String clave;

    @Schema(description = "Estado activo o inactivo", example = "true")
    private Boolean estado;

    @Schema(description = "Número de teléfono", example = "987654321")
    private String telefono;

    @Schema(description = "ID del rol asociado al usuario", example = "2")
    private Long id;
}