package com.Microservicio.Gestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Microservicio.Gestion.model.Servicio;
import com.Microservicio.Gestion.model.TipoServicio;
import com.Microservicio.Gestion.service.ServicioService;
import com.Microservicio.Gestion.service.TipoServicioService;

import lombok.Data;

@RestController
@RequestMapping("/api/v1/gestion")
@CrossOrigin(origins = "*")
public class GestionServiciosController {

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private TipoServicioService tipoServicioService;

    // --- Controlador de TipoServicio ---

    @GetMapping("/tipos")
    public ResponseEntity<List<TipoServicio>> listarTipos() {
        List<TipoServicio> tipos = tipoServicioService.obtenerTodosTipos();
        if (tipos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/tipos/{id}")
    public ResponseEntity<TipoServicio> obtenerTipo(@PathVariable Long id) {
        return ResponseEntity.ok(tipoServicioService.obtenerPorIdTipo(id));
    }

    @PostMapping("/tipos")
    public ResponseEntity<TipoServicio> crearTipo(@RequestBody TipoServicio tipo) {
        return ResponseEntity.ok(tipoServicioService.crear(tipo.getNombre()));
    }

    @PutMapping("/tipos/{id}")
    public ResponseEntity<TipoServicio> actualizarTipo(@PathVariable Long id, @RequestBody TipoServicio tipo) {
        return ResponseEntity.ok(tipoServicioService.actualizar(id, tipo.getNombre()));
    }

    @DeleteMapping("/tipos/{id}")
    public ResponseEntity<Void> eliminarTipo(@PathVariable Long id) {
        tipoServicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // --- Controlador de Servicio ---

    @GetMapping("/servicios")
    public ResponseEntity<List<Servicio>> listarServicios() {
        List<Servicio> servicios = servicioService.obtenerTodosServicios();
        if (servicios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(servicios);
    }

    @GetMapping("/servicios/{id}")
    public ResponseEntity<Servicio> obtenerServicio(@PathVariable Long id) {
        return ResponseEntity.ok(servicioService.obtenerPorIdServicio(id));
    }

    @PostMapping("/servicios")
    public ResponseEntity<Servicio> crearServicio(@RequestBody ServicioRequest request) {
        TipoServicio tipo = tipoServicioService.obtenerPorIdTipo(request.getIdTipo());
        Servicio nuevo = servicioService.crearServicio(
                request.getNombre(),
                request.getDescripcion(),
                request.getPrecio(),
                request.getIdReserva(),
                request.getIdComentario(),
                tipo
        );
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/servicios/{id}")
    public ResponseEntity<Servicio> actualizarServicio(@PathVariable Long id, @RequestBody ServicioRequest request) {
        TipoServicio tipo = tipoServicioService.obtenerPorIdTipo(request.getIdTipo());
        Servicio actualizado = servicioService.actualizar(
                id,
                request.getNombre(),
                request.getDescripcion(),
                request.getPrecio(),
                request.getIdComentario(),
                request.getIdReserva(),
                tipo
        );
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/servicios/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // --- DTO interno para Servicio ---
    @Data
    public static class ServicioRequest {
        private String nombre;
        private String descripcion;
        private Integer precio;
        private Long idTipo;
        private Long idReserva;
        private Long idComentario; // <--- AÑADIDO
    }
}