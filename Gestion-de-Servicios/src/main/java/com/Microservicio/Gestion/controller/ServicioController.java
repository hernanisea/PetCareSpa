package com.Microservicio.Gestion.controller;

import com.Microservicio.Gestion.model.Servicio;
import com.Microservicio.Gestion.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/servicios")
@CrossOrigin(origins = "*")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<Servicio>> listarServicios() {
        return ResponseEntity.ok(servicioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicio(@PathVariable Long id) {
        return ResponseEntity.ok(servicioService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Servicio> crearServicio(@RequestBody ServicioRequest request) {
        Servicio nuevo = servicioService.crear(request.getFechaReserva(), request.getEstado());
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizarServicio(@PathVariable Long id, @RequestBody ServicioRequest request) {
        Servicio actualizado = servicioService.actualizar(id, request.getFechaReserva(), request.getEstado());
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    public static class ServicioRequest {
        private LocalDateTime fechaReserva;
        private Boolean estado;

        public LocalDateTime getFechaReserva() {
            return fechaReserva;
        }

        public void setFechaReserva(LocalDateTime fechaReserva) {
            this.fechaReserva = fechaReserva;
        }

        public Boolean getEstado() {
            return estado;
        }

        public void setEstado(Boolean estado) {
            this.estado = estado;
        }
    }
}
