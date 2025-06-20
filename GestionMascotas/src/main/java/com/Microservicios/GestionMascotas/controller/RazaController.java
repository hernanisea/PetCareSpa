package com.Microservicios.GestionMascotas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Microservicios.GestionMascotas.model.Raza;
import com.Microservicios.GestionMascotas.service.RazaService;

@RestController
@CrossOrigin(origins = "*")
public class RazaController {

    @Autowired
    private RazaService razaService;

    @GetMapping("/api/v1/razas")
    public ResponseEntity<List<Raza>> listarRazas() {
        List<Raza> lista = razaService.obtenerTodas();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/api/v1/razas/{id}")
    public ResponseEntity<Raza> obtenerRaza(@PathVariable Long id) {
        return ResponseEntity.ok(razaService.obtenerPorId(id));
    }

    @PostMapping("/api/v1/razas")
    public ResponseEntity<Raza> crearRaza(@RequestBody Raza raza) {
        return ResponseEntity.ok(razaService.guardar(raza));
    }

    @DeleteMapping("/api/v1/razas/{id}")
    public ResponseEntity<Void> eliminarRaza(@PathVariable Long id) {
        razaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
