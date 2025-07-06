package com.Microservicios.GestionMascotas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "MascotaRequestDto", description = "DTO para crear o actualizar una mascota")
public class MascotaRequestDto {

    @Schema(description = "ID del usuario dueño", example = "1")
    private Long idUsuario;

    @Schema(description = "Nombre de la mascota", example = "Firulais")
    private String nombre;

    @Schema(description = "Edad de la mascota", example = "3")
    private Integer edad;

    @Schema(description = "Sexo de la mascota", example = "Macho")
    private String sexo;

    @Schema(description = "ID de la especie asociada", example = "1")
    private Long especieId;

    @Schema(description = "ID de la raza asociada", example = "4")
    private Long razaId;
}

