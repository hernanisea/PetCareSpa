package com.Microservicio.Gestion.de.Reservas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa una reserva en el sistema")
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    @Schema(description = "ID único de la reserva", example = "1")
    private Long idReserva;

    @Column(name = "fecha_reserva", nullable = false)
    @Schema(description = "Fecha y hora de la reserva", example = "2025-08-20T15:30:00")
    private LocalDateTime fechaReserva;

    @Column(name = "estado", nullable = false)
    @Schema(description = "Estado de la reserva (activa o cancelada)", example = "true")
    private Boolean estado;

    @Column(name = "fecha_creacion", nullable = false)
    @Schema(description = "Fecha de creación del registro", example = "2025-07-01T12:00:00")
    private LocalDateTime fechaCreacion;

    @JsonIgnore
    @Column(name = "usuario_id", nullable = false)
    @Schema(description = "ID del usuario asociado", example = "1")
    private Long usuarioId;

    @JsonIgnore
    @Column(name = "mascota_id", nullable = false)
    @Schema(description = "ID de la mascota asociada", example = "2")
    private Long mascotaId;
}
