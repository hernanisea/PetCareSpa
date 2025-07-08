package com.Microservicios.GestionUsuarios.dto;

import com.Microservicios.GestionUsuarios.model.RelacionesUsuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "UsuarioRequest", description = "DTO para la creación o actualización de usuarios")
public class UsuarioRequest {

    @NotBlank
    @Schema(description = "Nombre del usuario", example = "Juan")
    private String nombre;

    @NotBlank
    @Schema(description = "Apellido del usuario", example = "Pérez")
    private String apellido;

    @NotBlank
    @Email
    @Schema(description = "Correo electrónico del usuario. Debe ser único.", example = "juan.perez@demo.com")
    private String correo;

    @NotBlank
    @Schema(description = "Contraseña del usuario (en texto plano al momento de crear)", example = "12345678")
    private String clave;

    @NotNull
    @Schema(description = "Estado del usuario", example = "true")
    private Boolean estado;

    @NotBlank
    @Schema(description = "Número de teléfono del usuario", example = "987654321")
    private String telefono;

    @NotNull
    @Schema(description = "ID del rol asignado al usuario", example = "1")
    private Long idRol;

    @Schema(hidden = true)
    private RelacionesUsuario relaciones;


    
}
