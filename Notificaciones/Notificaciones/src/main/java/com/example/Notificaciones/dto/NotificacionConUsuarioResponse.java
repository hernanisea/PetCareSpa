package com.example.Notificaciones.dto;

import com.example.Notificaciones.model.Notificacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionConUsuarioResponse {

    private Notificacion notificacion;
    private Map<String, Object> usuario;
}
