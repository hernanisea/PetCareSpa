package com.Microservicios.Gestion.de.Direcciones.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "DTO para crear una nueva dirección")
public class DireccionRequest {

    @NotBlank
    @Schema(description = "Calle de la dirección", example = "Av. Siempre Viva 742")
    private String calle;

    @Schema(description = "Descripción adicional", example = "Departamento 3B, Edificio Central")
    private String descripcion;

    @NotNull
    @Schema(description = "Código postal", example = "8320000")
    private Integer codigoPostal;

    @NotNull
    @Schema(description = "ID del usuario asociado", example = "5")
    private Long usuarioId;

    @NotNull
    @Schema(description = "ID de la comuna asociada", example = "10")
    private Long idComuna;
}
