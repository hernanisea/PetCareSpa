package com.Microservicios.GestionInventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TratamientoRequest {

    @NotNull(message = "La cantidad no puede ser nula")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    private Double subtotal;

    @NotNull(message = "Debe proporcionar el ID del producto")
    private Long idProducto;
}