package com.Microservicios.HistorialClinico.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historial_clinico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "HistorialClinico", description = "Entidad que representa un historial clínico de una mascota atendida por un veterinario.")
public class HistorialClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    @Schema(description = "Identificador único del historial clínico", example = "1")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_historial", nullable = false)
    @Schema(description = "Fecha en que se registró el historial clínico", example = "2025-07-05", required = true)
    private Date fecha;

    @Column(length = 500)
    @Schema(description = "Información de antecedentes médicos del paciente", example = "Vacunas al día, antecedentes de alergias")
    private String antecedentes;

    @Column(length = 500)
    @Schema(description = "Comentarios adicionales del veterinario", example = "El paciente mostró ansiedad durante el examen")
    private String comentarios;

    @Column(length = 500)
    @Schema(description = "Diagnóstico clínico asignado al paciente", example = "Gripe canina")
    private String diagnostico;

    @Column(name = "usuario_id", nullable = false)
    @Schema(description = "ID del usuario (cliente) relacionado con este historial", example = "3", required = true)
    private Long usuarioId;

    @Column(name = "mascota_id", nullable = false)
    @Schema(description = "ID de la mascota asociada al historial clínico", example = "7", required = true)
    private Long mascotaId;
}
