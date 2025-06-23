package com.Microservicios.GestionUsuarios.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class UsuarioRequest {
    private String nombre;
    private String apellido;
    private String correo;
    private String clave;
    private Boolean estado;
    private String telefono;
    private Long idDireccion;
    private Long idMascota;
    private Long idComentario;
    private Long idNotificacion;
    private Long idReportes;
    private Long idHistorial;
    private Long id;


}
