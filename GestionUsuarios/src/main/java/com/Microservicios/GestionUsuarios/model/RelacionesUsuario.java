package com.Microservicios.GestionUsuarios.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
@Schema(name = "RelacionesUsuario", description = "Contiene los IDs de entidades relacionadas con el usuario")
public class RelacionesUsuario {

    @Schema(description = "ID de la dirección del usuario", example = "10")
    private Long idDireccion;

    @Schema(description = "ID de la mascota del usuario", example = "5")
    private Long idMascota;

    @Schema(description = "ID del comentario del usuario", example = "3")
    private Long idComentario;

    @Schema(description = "ID de la notificación del usuario", example = "8")
    private Long idNotificacion;

    @Schema(description = "ID del historial clínico del usuario", example = "15")
    private Long idHistorial;
}   
