package com.Microservicios.Gestion.Comentarios.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comentarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Comentario", description = "Entidad que representa un comentario de un usuario sobre una reserva")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del comentario", example = "10")
    private Long idComentario;

    @Schema(description = "Contenido textual del comentario", example = "Muy buena atención y puntualidad.")
    private String contenido;

    @Schema(description = "Fecha en que se registró el comentario", example = "2025-07-06")
    private String fechaCreacion;

    @Schema(description = "Indica si el comentario está activo", example = "true")
    private boolean estado;

    @Schema(description = "ID del usuario autor del comentario", example = "5")
    private Long idUsuario;

    @Schema(description = "ID de la reserva a la cual pertenece el comentario", example = "12")
    private Long idReserva;
}