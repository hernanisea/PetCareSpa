package com.example.Notificaciones.dto;

import java.util.Map;

import com.example.Notificaciones.model.Notificacion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "NotificacionConUsuarioResponse", description = "Respuesta que contiene la notificación y los datos del usuario")
public class NotificacionConUsuarioResponse {

    @Schema(description = "Datos de la notificación")
    private Notificacion notificacion;

    @Schema(description = "Información del usuario relacionado")
    private Map<String, Object> usuario;
}