package com.Microservicio.Gestion.de.Reservas.controller;

import com.Microservicio.Gestion.de.Reservas.dto.ReservaRequest;
import com.Microservicio.Gestion.de.Reservas.model.Reservas;
import com.Microservicio.Gestion.de.Reservas.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "Crear una nueva reserva")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente",
                content = @Content(schema = @Schema(implementation = Reservas.class))),
        @ApiResponse(responseCode = "404", description = "Error al crear reserva (usuario o mascota no encontrados)")
    })
    @PostMapping
    public ResponseEntity<?> crearReserva(@Valid @RequestBody ReservaRequest request) {
        try {
            Reservas creada = reservaService.guardarReserva(request);
            return ResponseEntity.status(201).body(creada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Operation(summary = "Actualizar una reserva por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reserva actualizada correctamente",
                content = @Content(schema = @Schema(implementation = Reservas.class))),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada o error de datos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReserva(@PathVariable Long id, @Valid @RequestBody ReservaRequest request) {
        try {
            Reservas actualizada = reservaService.actualizarReserva(id, request);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar una reserva por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Reserva eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable Long id) {
        try {
            reservaService.eliminarReserva(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Operation(summary = "Listar todas las reservas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservas listadas correctamente",
                content = @Content(schema = @Schema(implementation = Reservas.class))),
        @ApiResponse(responseCode = "204", description = "No hay reservas registradas")
    })
    @GetMapping
    public ResponseEntity<List<Reservas>> listar() {
        List<Reservas> reservas = reservaService.listarReservas();
        return reservas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(reservas);
    }
}
