package com.example.Notificaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.Notificaciones.dto.NotificacionRequest;
import com.example.Notificaciones.dto.NotificacionConUsuarioResponse;
import com.example.Notificaciones.model.Notificacion;
import com.example.Notificaciones.service.NotificacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/notificaciones")
@Validated
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<Notificacion>> obtenerTodas() {
        List<Notificacion> lista = notificacionService.obtenerTodas();
        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        List<Notificacion> lista = notificacionService.obtenerPorUsuario(usuarioId);
        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{usuarioId}/con-datos")
    public ResponseEntity<List<NotificacionConUsuarioResponse>> obtenerPorUsuarioConDatos(@PathVariable Long usuarioId) {
        List<NotificacionConUsuarioResponse> lista = notificacionService.obtenerPorUsuarioConDatos(usuarioId);
        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Notificacion> crear(@Valid @RequestBody NotificacionRequest request) {
        try {
            Notificacion creada = notificacionService.crearDesdeDTO(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo crear la notificación", e);
        }
    }

    @PutMapping("/{id}/leido")
    public ResponseEntity<Notificacion> marcarLeida(@PathVariable Long id) {
        Notificacion actualizada = notificacionService.marcarComoLeida(id);
        return ResponseEntity.ok(actualizada);
    }
}
