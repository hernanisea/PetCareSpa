package com.example.GestionDeServicios.dto;

import lombok.Data;

@Data
public class ServicioRequestDto {
    private String nombre;
    private String descripcion;
    private Integer precio;
    private Long idTipoServicio;
}
