package com.Microservicios.GestionUsuarios.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "RolConUsuariosResponse", description = "DTO que representa un rol con su lista de usuarios")
public class RolConUsuariosResponse {

    @Schema(description = "ID del rol", example = "2")
    private Long id;

    @Schema(description = "Nombre del rol", example = "Cliente")
    private String nombre;

    @Schema(description = "Lista de usuarios que tienen este rol")
    private List<UsuarioResponseRol> usuarios;
}