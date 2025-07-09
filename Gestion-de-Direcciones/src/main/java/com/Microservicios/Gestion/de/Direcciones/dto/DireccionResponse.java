package com.Microservicios.Gestion.de.Direcciones.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO con la información pública de la dirección")
public class DireccionResponse {

    @Schema(description = "ID único de la dirección", example = "100")
    private Long idDireccion;

    private String calle;
    private String descripcion;
    private int codigoPostal;
    private Long usuarioId;

    @Schema(description = "Nombre de la comuna", example = "Santiago")
    private String comuna;

    @Schema(description = "Nombre de la región", example = "Región Metropolitana")
    private String region;
}
