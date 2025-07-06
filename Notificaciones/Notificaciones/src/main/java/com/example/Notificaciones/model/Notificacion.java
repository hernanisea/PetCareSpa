package com.example.Notificaciones.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notificaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Notificacion", description = "Entidad que representa una notificación en el sistema")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la notificación", example = "1001")
    private Long idNotificacion;

    @Schema(description = "ID del usuario al que pertenece la notificación", example = "1")
    private Long usuarioId;

    @Column(length = 255)
    @Schema(description = "Mensaje del contenido de la notificación", example = "Su cita fue confirmada con éxito")
    private String mensaje;

    @Schema(description = "Fecha de creación de la notificación", example = "2025-07-06T12:00:00")
    private Date fecha;

    @Schema(description = "Indica si la notificación ha sido leída", example = "false")
    private Boolean leido;

    @Schema(description = "Correo del creador de la notificación", example = "admin@demo.com")
    private String creadoPor;
}