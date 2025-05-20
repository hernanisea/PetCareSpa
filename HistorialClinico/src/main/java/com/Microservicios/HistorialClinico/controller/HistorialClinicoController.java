package com.Microservicios.HistorialClinico.controller;

import java.util.Date;
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

import com.Microservicios.HistorialClinico.model.HistorialClinico;
import com.Microservicios.HistorialClinico.service.HistorialClinicoService;

@RestController
@RequestMapping("/api/v1/historiales")
@CrossOrigin(origins = "*")
public class HistorialClinicoController {

    @Autowired
    private final HistorialClinicoService historialClinicoService;

    public HistorialClinicoController(HistorialClinicoService historialClinicoService) {
        this.historialClinicoService = historialClinicoService;
    }

    @GetMapping
    public ResponseEntity<List<HistorialClinico>> listarHistoriales() {
        return ResponseEntity.ok(historialClinicoService.obtenerHistoriales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialClinico> obtenerHistorial(@PathVariable Long id) {
        return ResponseEntity.ok(historialClinicoService.obtenerHistorialPorId(id));
    }

    @PostMapping
    public ResponseEntity<HistorialClinico> crearHistorial(@RequestBody HistorialRequest request) {
        HistorialClinico nuevo = historialClinicoService.crearHistorial(
                request.getFechaHistorial(),
                request.getAntecedentes(),
                request.getComentarios(),
                request.getDiagnostico()
        );
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialClinico> actualizarHistorial(@PathVariable Long id, @RequestBody HistorialRequest request) {
        HistorialClinico actualizado = historialClinicoService.actualizarHistorial(
                id,
                request.getFechaHistorial(),
                request.getAntecedentes(),
                request.getComentarios(),
                request.getDiagnostico()
        );
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistorial(@PathVariable Long id) {
        historialClinicoService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }

    // DTO de entrada
    public static class HistorialRequest {
        private Date fechaHistorial;
        private String antecedentes;
        private String comentarios;
        private String diagnostico;

        public Date getFechaHistorial() { return fechaHistorial; }
        public void setFechaHistorial(Date fechaHistorial) { this.fechaHistorial = fechaHistorial; }

        public String getAntecedentes() { return antecedentes; }
        public void setAntecedentes(String antecedentes) { this.antecedentes = antecedentes; }

        public String getComentarios() { return comentarios; }
        public void setComentarios(String comentarios) { this.comentarios = comentarios; }

        public String getDiagnostico() { return diagnostico; }
        public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    }
}
