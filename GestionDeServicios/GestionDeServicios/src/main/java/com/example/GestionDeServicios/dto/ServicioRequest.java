package com.example.GestionDeServicios.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(name = "ServicioRequest", description = "DTO para crear o actualizar un servicio")
public class ServicioRequest {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Schema(description = "Nombre del servicio", example = "Desparasitación externa", required = true)
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Schema(description = "Descripción del servicio", example = "Tratamiento para eliminar pulgas y garrapatas", required = true)
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    @Schema(description = "Precio del servicio", example = "10000", required = true)
    private Integer precio;

    @NotNull(message = "El ID del tipo de servicio es obligatorio")
    @Schema(description = "ID del tipo de servicio al que pertenece", example = "2", required = true)
    private Long tipoServicioId;
}
