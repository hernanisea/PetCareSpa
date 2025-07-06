package com.Microservicios.Gestion.Comentarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ComentarioRequest", description = "DTO para crear o actualizar un comentario")
public class ComentarioRequest {

    @Schema(description = "Contenido textual del comentario", example = "Excelente atención del veterinario.")
    private String contenido;

    @Schema(description = "Fecha en que se creó el comentario (formato yyyy-MM-dd)", example = "2025-07-06")
    private String fechaCreacion;

    @Schema(description = "Indica si el comentario está activo", example = "true")
    private boolean estado;

    @Schema(description = "ID del usuario que realizó el comentario", example = "3")
    private Long idUsuario;

    @Schema(description = "ID de la reserva asociada al comentario", example = "7")
    private Long idReserva;
}