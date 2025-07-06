package com.example.Notificaciones.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor

public class NotificacionRequest {

 @NotNull
    @Schema(description = "ID del usuario que recibirá la notificación", example = "101", required = true)
    private Long usuarioId;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Mensaje de la notificación", example = "Tienes una nueva consulta", required = true)
    private String mensaje;
    
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}