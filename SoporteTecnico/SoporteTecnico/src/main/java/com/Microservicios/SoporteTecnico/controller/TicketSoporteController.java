package com.Microservicios.SoporteTecnico.controller;

import com.Microservicios.SoporteTecnico.model.TicketSoporte;
import com.Microservicios.SoporteTecnico.services.TicketSoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/soporte")
public class TicketSoporteController {

    @Autowired
    private TicketSoporteService ticketService;

    // Crear nuevo ticket
    @PostMapping
    public ResponseEntity<TicketSoporte> crearTicket(@RequestBody TicketSoporte ticket) {
        TicketSoporte nuevo = ticketService.crearTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Obtener ticket por ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketSoporte> obtenerPorId(@PathVariable Long id) {
        TicketSoporte ticket = ticketService.obtenerPorId(id);
        return ResponseEntity.ok(ticket);
    }

    // Obtener historial por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TicketSoporte>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<TicketSoporte> tickets = ticketService.obtenerPorUsuario(usuarioId);
        if (tickets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tickets);
    }

    // Actualizar estado del ticket
    @PutMapping("/{id}/estado")
    public ResponseEntity<TicketSoporte> actualizarEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
        TicketSoporte actualizado = ticketService.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(actualizado);
    }

    // Agregar respuesta
    @PutMapping("/{id}/respuesta")
    public ResponseEntity<TicketSoporte> agregarRespuesta(@PathVariable Long id, @RequestBody String respuesta) {
        TicketSoporte actualizado = ticketService.agregarRespuesta(id, respuesta);
        return ResponseEntity.ok(actualizado);
    }
}