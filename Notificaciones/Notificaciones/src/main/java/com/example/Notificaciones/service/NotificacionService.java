package com.example.Notificaciones.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Notificaciones.client.UsuarioClient;
import com.example.Notificaciones.dto.NotificacionConUsuarioResponse;
import com.example.Notificaciones.dto.NotificacionRequest;
import com.example.Notificaciones.model.Notificacion;
import com.example.Notificaciones.repository.NotificacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotificacionService {

    @Autowired NotificacionRepository notificacionRepository;

    @Autowired UsuarioClient usuarioClient;

    public Notificacion crearDesdeDTO(NotificacionRequest request, String correoCreador) {
        validarUsuarioExistente(request.getUsuarioId());

        Notificacion noti = new Notificacion();
        noti.setUsuarioId(request.getUsuarioId());
        noti.setMensaje(request.getMensaje());
        noti.setLeido(false);
        noti.setFecha(new Date());
        noti.setCreadoPor(correoCreador);
        return notificacionRepository.save(noti);
    }

    public List<Notificacion> obtenerTodas() {
        return notificacionRepository.findAll();
    }

    public List<Notificacion> obtenerPorUsuario(Long usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    public List<NotificacionConUsuarioResponse> obtenerPorUsuarioConDatos(Long usuarioId) {
        validarUsuarioExistente(usuarioId);
        List<Notificacion> notificaciones = notificacionRepository.findByUsuarioId(usuarioId);
        return notificaciones.stream().map(noti -> {
            Map<String, Object> usuario = usuarioClient.obtenerUsuarioPorId(noti.getUsuarioId());
            return new NotificacionConUsuarioResponse(noti, usuario);
        }).toList();
    }

    public List<Notificacion> obtenerPorCreador(String correo) {
        return notificacionRepository.findByCreadoPor(correo);
    }

    public Notificacion marcarComoLeida(Long id) {
        return notificacionRepository.findById(id)
                .map(noti -> {
                    noti.setLeido(true);
                    return notificacionRepository.save(noti);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Notificación no encontrada con ID: " + id));
    }

    private void validarUsuarioExistente(Long usuarioId) {
        try {
            usuarioClient.obtenerUsuarioPorId(usuarioId);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "No se puede crear notificación: usuario con ID " + usuarioId + " no existe.");
        }
    }
}
