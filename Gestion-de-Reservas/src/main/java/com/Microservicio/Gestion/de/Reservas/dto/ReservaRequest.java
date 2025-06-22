package com.Microservicio.Gestion.de.Reservas.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservaRequest {

    @NotNull(message = "La fecha de la reserva no puede ser nula")
    @Future(message = "La fecha debe ser en el futuro")
    private LocalDateTime fechaReserva;

    @NotNull(message = "El ID de usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El ID de la mascota es obligatorio")
    private Long mascotaId;
}
