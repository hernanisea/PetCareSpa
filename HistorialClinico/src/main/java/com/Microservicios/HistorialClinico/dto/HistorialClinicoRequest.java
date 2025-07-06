package com.Microservicios.HistorialClinico.dto;

import java.util.Date;
import lombok.Data;

@Data
public class HistorialClinicoRequest {
    private Date fecha;
    private String antecedentes;
    private String comentarios;
    private String diagnostico;
    private Long usuarioId;
    private Long mascotaId;
}
