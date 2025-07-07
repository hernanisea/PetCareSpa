package com.Microservicios.HistorialClinico.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "HistorialClinicoRequest", description = "DTO para la creación o actualización de un historial clínico")
public class HistorialClinicoRequest {

    @Schema(description = "Fecha del historial clínico", example = "2025-07-05", required = true)
    private Date fecha;

    @Schema(description = "Información de antecedentes médicos del paciente", example = "Sin antecedentes relevantes")
    private String antecedentes;

    @Schema(description = "Comentarios adicionales del veterinario", example = "Paciente tranquilo durante la revisión")
    private String comentarios;

    @Schema(description = "Diagnóstico clínico asignado", example = "Infección respiratoria")
    private String diagnostico;

    @Schema(description = "ID del usuario asociado (cliente)", example = "5", required = true)
    private Long usuarioId;

    @Schema(description = "ID de la mascota asociada", example = "12", required = true)
    private Long mascotaId;
}
