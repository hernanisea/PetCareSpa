package com.Microservicios.HistorialClinico.controller;

import java.util.List;

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

import com.Microservicios.HistorialClinico.dto.HistorialClinicoRequest;
import com.Microservicios.HistorialClinico.model.HistorialClinico;
import com.Microservicios.HistorialClinico.service.HistorialClinicoService;

@RestController
@RequestMapping("/api/v1/historiales")
@CrossOrigin(origins = "*")
public class HistorialClinicoController {

    private final HistorialClinicoService historialClinicoService;

    public HistorialClinicoController(HistorialClinicoService historialClinicoService) {
        this.historialClinicoService = historialClinicoService;
    }

    @GetMapping
    public ResponseEntity<List<HistorialClinico>> listarHistoriales() {
        return ResponseEntity.ok(historialClinicoService.listarHistoriales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialClinico> obtenerHistorial(@PathVariable Long id) {
        return ResponseEntity.ok(historialClinicoService.listarHistoriales().stream()
                .filter(h -> h.getIdHistorial().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Historial no encontrado")));
    }

    @PostMapping
    public ResponseEntity<HistorialClinico> crearHistorial(@RequestBody HistorialClinicoRequest request) {
        return ResponseEntity.ok(historialClinicoService.guardarHistorial(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialClinico> actualizarHistorial(@PathVariable Long id, @RequestBody HistorialClinicoRequest request) {
        return ResponseEntity.ok(historialClinicoService.actualizarHistorial(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistorial(@PathVariable Long id) {
        historialClinicoService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }
}  
