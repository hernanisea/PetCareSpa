package com.example.GestionDeServicios.controller;

import com.example.GestionDeServicios.model.TipoServicio;
import com.example.GestionDeServicios.services.TipoServicioService;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tiposervicios")
@CrossOrigin(origins = "*")
public class TipoServicioController {

    private final TipoServicioService tipoServicioService;

    public TipoServicioController(TipoServicioService tipoServicioService) {
        this.tipoServicioService = tipoServicioService;
    }

    @GetMapping
    public ResponseEntity<List<TipoServicio>> getAllTipoServicios() {
        List<TipoServicio> lista = tipoServicioService.findAll();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTipoServicioById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tipoServicioService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createTipoServicio(@RequestBody TipoServicioRequest request) {
        try {
            TipoServicio creado = tipoServicioService.save(request.getNombre());
            return ResponseEntity.status(201).body(creado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear tipo de servicio: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTipoServicioById(@PathVariable Long id) {
        try {
            tipoServicioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // DTO Interno
    @Data
    public static class TipoServicioRequest {
        @NotBlank(message = "El nombre no puede estar vacío")
        private String nombre;
    }
}
