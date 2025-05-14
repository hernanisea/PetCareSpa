package com.Microservicio.Gestion.de.Reservas.controller;

import com.Microservicio.Gestion.de.Reservas.model.Reservas;
import com.Microservicio.Gestion.de.Reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<Reservas>> listarReservas() {
        return ResponseEntity.ok(reservaService.obtenerReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservas> obtenerReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.obtenerReservaPorId(id));
    }

    @PostMapping
    public ResponseEntity<Reservas> crearReserva(@RequestBody ReservaRequest request) {
        Reservas nueva = reservaService.crearReserva(request.getFechaReserva());
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservas> actualizarReserva(@PathVariable Long id, @RequestBody ReservaRequest request) {
        Reservas actualizada = reservaService.actualizarReserva(id, request.getFechaReserva(), request.getEstado());
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }

    // DTO de entrada
    public static class ReservaRequest {
        private LocalDateTime fechaReserva;
        private Boolean estado;

        public LocalDateTime getFechaReserva() { return fechaReserva; }
        public void setFechaReserva(LocalDateTime fechaReserva) { this.fechaReserva = fechaReserva; }

        public Boolean getEstado() { return estado; }
        public void setEstado(Boolean estado) { this.estado = estado; }
    }
}
