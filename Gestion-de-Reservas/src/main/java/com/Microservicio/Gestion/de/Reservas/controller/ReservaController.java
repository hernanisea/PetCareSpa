package com.Microservicio.Gestion.de.Reservas.controller;

import com.Microservicio.Gestion.de.Reservas.dto.ReservaRequest;
import com.Microservicio.Gestion.de.Reservas.model.Reservas;
import com.Microservicio.Gestion.de.Reservas.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<?> crearReserva(@Valid @RequestBody ReservaRequest request) {
        try {
            Reservas creada = reservaService.guardarReserva(request);
            return ResponseEntity.status(201).body(creada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReserva(@PathVariable Long id, @Valid @RequestBody ReservaRequest request) {
        try {
            Reservas actualizada = reservaService.actualizarReserva(id, request);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable Long id) {
        try {
            reservaService.eliminarReserva(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Reservas>> listar() {
        List<Reservas> reservas = reservaService.listarReservas();
        return reservas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(reservas);
    }
}
