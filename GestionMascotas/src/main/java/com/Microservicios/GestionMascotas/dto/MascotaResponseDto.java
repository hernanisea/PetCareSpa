package com.Microservicios.GestionMascotas.dto;

import lombok.Data;

@Data
public class MascotaResponseDto {
    private Long idMascota;
    private String nombre;
    private Integer edad;
    private String sexo;
    private String especie;
    private String raza;
    private Long idUsuario;
}
