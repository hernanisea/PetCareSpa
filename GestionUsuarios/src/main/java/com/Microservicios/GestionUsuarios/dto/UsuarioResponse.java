package com.Microservicios.GestionUsuarios.dto;

import lombok.Data;
import java.util.Map;

@Data
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private Boolean estado;
    private Map<String, Object> direccion; // Aquí vendrán los datos completos de la dirección
    private String rol;
}
