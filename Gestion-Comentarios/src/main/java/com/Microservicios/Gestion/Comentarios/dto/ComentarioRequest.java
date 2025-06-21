package com.Microservicios.Gestion.Comentarios.dto;

import lombok.Data;

@Data
public class ComentarioRequest {
    private String contenido;
    private String fechaCreacion;
    private Boolean estado;
    private Long idUsuario;
    private Long idReserva;
}
