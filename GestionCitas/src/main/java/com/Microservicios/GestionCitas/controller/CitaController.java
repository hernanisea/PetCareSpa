package com.Microservicios.GestionCitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Microservicios.GestionCitas.model.Cita;
import com.Microservicios.GestionCitas.service.CitaService;

@RestController
@RequestMapping("/api/v1/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> listarTodos() {
        List<Cita> lista = citaService.listarCitas();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

        
    @GetMapping("/{idCita}")
        public ResponseEntity<Cita> obtenerUsuario(@PathVariable Long idCita) {
            Cita cita = citaService.obtenerCita(idCita);
            if (cita != null) {
                return ResponseEntity.ok(cita);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    @GetMapping("/citas/{veterinarioId}")
    public ResponseEntity<List<Cita>> obtenerPorVeterinarioId(@PathVariable Long veterinarioId) {
        List<Cita> lista = citaService.obtenerPorVeterinarioId(veterinarioId);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Cita> crear(@RequestBody Cita cita) {
        return ResponseEntity.status(201).body(citaService.reservarCita(cita));
    }

      @DeleteMapping("/{idCita}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer idCita) {
        citaService.eliminarCita(idCita);
        return ResponseEntity.noContent().build();
    }

}