package com.Microservicios.GestionMascotas.dto;

import lombok.Data;

@Data
public class MascotaRequestDto {
    private Long idUsuario;
    private String nombre;
    private Integer edad;
    private String sexo;
    private Long especieId;
    private Long razaId;
}
