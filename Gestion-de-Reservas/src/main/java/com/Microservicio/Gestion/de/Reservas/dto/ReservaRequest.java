package com.Microservicio.Gestion.de.Reservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa la información necesaria para crear o actualizar una reserva")
public class ReservaRequest {

    @NotNull(message = "La fecha de la reserva no puede ser nula")
    @Future(message = "La fecha debe ser en el futuro")
    @Schema(description = "Fecha y hora programada para la reserva", example = "2025-08-20T15:30:00")
    private LocalDateTime fechaReserva;

    @NotNull(message = "El ID de usuario es obligatorio")
    @Schema(description = "ID del usuario que realiza la reserva", example = "1")
    private Long usuarioId;

    @NotNull(message = "El ID de la mascota es obligatorio")
    @Schema(description = "ID de la mascota asociada a la reserva", example = "3")
    private Long mascotaId;
}
