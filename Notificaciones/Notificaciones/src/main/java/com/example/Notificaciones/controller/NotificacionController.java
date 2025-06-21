package com.example.Notificaciones.controller;

import com.example.Notificaciones.dto.NotificacionConUsuarioResponse;
import com.example.Notificaciones.dto.NotificacionRequest;
import com.example.Notificaciones.model.Notificacion;
import com.example.Notificaciones.security.JwtUtil;
import com.example.Notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@Validated
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<Notificacion>> obtenerTodas() {
        List<Notificacion> lista = notificacionService.obtenerTodas();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        List<Notificacion> lista = notificacionService.obtenerPorUsuario(usuarioId);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{usuarioId}/con-datos")
    public ResponseEntity<List<NotificacionConUsuarioResponse>> obtenerPorUsuarioConDatos(@PathVariable Long usuarioId) {
        List<NotificacionConUsuarioResponse> lista = notificacionService.obtenerPorUsuarioConDatos(usuarioId);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/creadas-por/{correo}")
    public ResponseEntity<List<Notificacion>> obtenerPorCreador(@PathVariable String correo) {
        List<Notificacion> lista = notificacionService.obtenerPorCreador(correo);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/mis-notificaciones")
    public ResponseEntity<List<Notificacion>> obtenerMisNotificaciones(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        try {
            String correo = jwtUtil.extraerCorreoDesdeToken(authHeader);
            List<Notificacion> lista = notificacionService.obtenerPorCreador(correo);
            return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido o ausente", e);
        }
    }

    @PostMapping
    public ResponseEntity<Notificacion> crear(@Valid @RequestBody NotificacionRequest request,
                                              @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        try {
            String correoUsuario = jwtUtil.extraerCorreoDesdeToken(authHeader);
            Notificacion creada = notificacionService.crearDesdeDTO(request, correoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido o no proporcionado", e);
        }
    }

    @PutMapping("/{id}/leido")
    public ResponseEntity<Notificacion> marcarLeida(@PathVariable Long id) {
        Notificacion actualizada = notificacionService.marcarComoLeida(id);
        return ResponseEntity.ok(actualizada);
    }
}
