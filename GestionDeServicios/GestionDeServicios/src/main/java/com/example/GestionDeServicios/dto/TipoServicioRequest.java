package com.example.GestionDeServicios.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(name = "TipoServicioRequest", description = "DTO para crear o actualizar un tipo de servicio")
public class TipoServicioRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre del tipo de servicio", example = "Cirugías", required = true)
    private String nombre;
}
