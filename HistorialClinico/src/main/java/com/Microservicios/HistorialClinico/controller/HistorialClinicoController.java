package com.Microservicios.HistorialClinico.controller;

import com.Microservicios.HistorialClinico.model.HistorialClinico;
import com.Microservicios.HistorialClinico.service.HistorialClinicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/historiales")
public class HistorialClinicoController {

    @Autowired
    private HistorialClinicoService historialService;

    @GetMapping
    public ResponseEntity<List<HistorialClinico>> listarTodos() {
        List<HistorialClinico> lista = historialService.obtenerTodos();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialClinico> obtenerPorId(@PathVariable Long id) {
        return historialService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<HistorialClinico>> obtenerPorMascotaId(@PathVariable Long mascotaId) {
        List<HistorialClinico> lista = historialService.obtenerPorMascotaId(mascotaId);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<HistorialClinico> crear(@RequestBody HistorialClinico historial) {
        return ResponseEntity.status(201).body(historialService.guardar(historial));
    }
}
