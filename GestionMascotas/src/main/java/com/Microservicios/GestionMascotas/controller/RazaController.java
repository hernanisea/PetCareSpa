package com.Microservicios.GestionMascotas.controller;

import com.Microservicios.GestionMascotas.model.Raza;
import com.Microservicios.GestionMascotas.service.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/razas")
@CrossOrigin(origins = "*")
public class RazaController {

    @Autowired
    private RazaService razaService;

    @GetMapping
    public ResponseEntity<List<Raza>> listarRazas() {
        List<Raza> lista = razaService.obtenerTodas();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Raza> obtenerRaza(@PathVariable Long id) {
        return ResponseEntity.ok(razaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Raza> actualizarRaza(@PathVariable Long id, @RequestBody Raza raza) {
        Raza actualizada = razaService.actualizar(id, raza);
        return ResponseEntity.ok(actualizada);
    }

    @PostMapping
    public ResponseEntity<Raza> crearRaza(@RequestBody Raza raza) {
        return ResponseEntity.ok(razaService.guardar(raza));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRaza(@PathVariable Long id) {
        razaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}