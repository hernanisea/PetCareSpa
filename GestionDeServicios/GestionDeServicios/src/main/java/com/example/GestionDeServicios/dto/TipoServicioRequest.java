package com.example.GestionDeServicios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO para crear o actualizar un tipo de servicio")
public class TipoServicioRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre del tipo de servicio", example = "Consulta veterinaria", required = true)
    private String nombre;
}
