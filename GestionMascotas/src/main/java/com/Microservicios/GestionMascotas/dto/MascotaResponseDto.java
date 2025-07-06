package com.Microservicios.GestionMascotas.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;


@Data
@Schema(name = "MascotaResponseDto", description = "DTO con la información pública de una mascota")
public class MascotaResponseDto {

    @Schema(description = "ID único de la mascota", example = "10")
    private Long idMascota;

    @Schema(description = "Nombre de la mascota", example = "Max")
    private String nombre;

    @Schema(description = "Edad de la mascota", example = "5")
    private Integer edad;

    @Schema(description = "Sexo de la mascota", example = "Macho")
    private String sexo;

    @Schema(description = "Nombre de la especie", example = "Felina")
    private String especie;

    @Schema(description = "Nombre de la raza", example = "Siames")
    private String raza;

    @Schema(description = "ID del usuario dueño", example = "2")
    private Long idUsuario;
}
