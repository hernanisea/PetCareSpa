package com.Microservicios.Gestion.Comentarios.dto;

import lombok.Data;

@Data
public class ComentarioResponse {
    private Long idComentario;
    private String contenido;
    private String fechaCreacion;
    private Boolean estado;
    private Long idReserva;

    private Long idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
}
