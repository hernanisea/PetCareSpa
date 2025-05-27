package com.Microservicios.Reportes.controller;

import com.Microservicios.Reportes.model.Reporte;
import com.Microservicios.Reportes.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    @Autowired
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ResponseEntity<List<Reporte>> listarReportes() {
        return ResponseEntity.ok(reporteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> obtenerReporte(@PathVariable Long id) {
        return ResponseEntity.ok(reporteService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Reporte> crearReporte(@RequestBody ReporteRequest request) {
        Reporte nuevo = new Reporte();
        nuevo.setTitulo(request.getTitulo());
        nuevo.setDescripcion(request.getDescripcion());
        nuevo.setFechaCreacion(request.getFechaCreacion());

        return ResponseEntity.ok(reporteService.guardar(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> actualizarReporte(@PathVariable Long id, @RequestBody ReporteRequest request) {
        Reporte existente = reporteService.obtenerPorId(id);
        existente.setTitulo(request.getTitulo());
        existente.setDescripcion(request.getDescripcion());
        existente.setFechaCreacion(request.getFechaCreacion());

        return ResponseEntity.ok(reporteService.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        reporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // DTO de entrada
    public static class ReporteRequest {
        private String titulo;
        private String descripcion;
        private Date fechaCreacion;

        public String getTitulo() { return titulo; }
        public void setTitulo(String titulo) { this.titulo = titulo; }

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

        public Date getFechaCreacion() { return fechaCreacion; }
        public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    }
}
