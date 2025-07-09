package com.Microservicios.Gestion.de.Direcciones.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para creación de direcciones")
public class DireccionRequestDto {
    
    @Schema(description = "Calle de la dirección", example = "Av. Siempre Viva 742")
    private String calle;

    @Schema(description = "Descripción adicional", example = "Departamento 3B, Edificio Central")
    private String descripcion;

    @Schema(description = "Código postal", example = "8320000")
    private int codigoPostal;

    @Schema(description = "ID del usuario al que pertenece la dirección", example = "5")
    private Long usuarioId;

    @Schema(description = "ID de la comuna", example = "10")
    private Long idComuna;
}
