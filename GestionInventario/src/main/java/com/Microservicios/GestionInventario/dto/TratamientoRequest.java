package com.Microservicios.GestionInventario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "TratamientoRequest", description = "DTO para crear un tratamiento con un producto específico")
public class TratamientoRequest {

    @NotNull(message = "La cantidad no puede ser nula")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Schema(description = "Cantidad de producto a aplicar", example = "3", required = true)
    private Integer cantidad;

    @Schema(description = "Subtotal del tratamiento (puede calcularse automáticamente)", example = "25990.0")
    private Double subtotal;

    @NotNull(message = "Debe proporcionar el ID del producto")
    @Schema(description = "ID del producto utilizado", example = "5", required = true)
    private Long idProducto;
}
