package com.example.Notificaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Notificaciones.dto.NotificacionConUsuarioResponse;
import com.example.Notificaciones.dto.NotificacionRequest;
import com.example.Notificaciones.model.Notificacion;
import com.example.Notificaciones.security.JwtUtil;
import com.example.Notificaciones.service.NotificacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/notificaciones")
@Validated
public class NotificacionController {

    @Autowired
    NotificacionService notificacionService;

    @Autowired
    JwtUtil jwtUtil;

    @Operation(summary = "Obtener todas las notificaciones")
    @ApiResponse(responseCode = "200", description = "Lista de notificaciones obtenida",
            content = @Content(schema = @Schema(implementation = Notificacion.class)))
    @GetMapping
    public ResponseEntity<List<Notificacion>> obtenerTodas() {
        List<Notificacion> lista = notificacionService.obtenerTodas();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener notificaciones por ID de usuario")
    @ApiResponse(responseCode = "200", description = "Notificaciones del usuario encontradas")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        List<Notificacion> lista = notificacionService.obtenerPorUsuario(usuarioId);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener notificaciones de un usuario con sus datos")
    @ApiResponse(responseCode = "200", description = "Notificaciones con datos del usuario obtenidas")
    @GetMapping("/usuario/{usuarioId}/con-datos")
    public ResponseEntity<List<NotificacionConUsuarioResponse>> obtenerPorUsuarioConDatos(@PathVariable Long usuarioId) {
        List<NotificacionConUsuarioResponse> lista = notificacionService.obtenerPorUsuarioConDatos(usuarioId);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener notificaciones creadas por un correo específico")
    @ApiResponse(responseCode = "200", description = "Notificaciones creadas por el usuario encontradas")
    @GetMapping("/creadas-por/{correo}")
    public ResponseEntity<List<Notificacion>> obtenerPorCreador(@PathVariable String correo) {
        List<Notificacion> lista = notificacionService.obtenerPorCreador(correo);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener mis notificaciones a partir del JWT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notificaciones del usuario autenticado"),
        @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
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

    @Operation(summary = "Obtener una notificación por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notificación encontrada",
                content = @Content(schema = @Schema(implementation = Notificacion.class))),
        @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerPorId(@PathVariable Long id) {
        Notificacion noti = notificacionService.obtenerPorId(id);
        return ResponseEntity.ok(noti);
    }

    @Operation(summary = "Crear una nueva notificación (requiere JWT)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Notificación creada exitosamente",
                content = @Content(schema = @Schema(implementation = Notificacion.class))),
        @ApiResponse(responseCode = "401", description = "Token inválido o no proporcionado")
    })
    @PostMapping
    public ResponseEntity<Notificacion> crear(@Valid @RequestBody NotificacionRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        try {
            // Primero validar si el usuario existe
            notificacionService.validarUsuarioExistente(request.getUsuarioId());

            // Luego extraer el correo del creador
            String correoUsuario = jwtUtil.extraerCorreoDesdeToken(authHeader);

            // Crear notificación
            Notificacion creada = notificacionService.crearDesdeDTO(request, correoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);

        } catch (ResponseStatusException ex) {
            throw ex; // re-lanzar excepciones personalizadas (como usuario inexistente)
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido o no proporcionado", e);
        }
    }

    @Operation(summary = "Marcar una notificación como leída por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notificación actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @PutMapping("/{id}/leido")
    public ResponseEntity<Notificacion> marcarLeida(@PathVariable Long id) {
        Notificacion actualizada = notificacionService.marcarComoLeida(id);
        return ResponseEntity.ok(actualizada);
    }
}
