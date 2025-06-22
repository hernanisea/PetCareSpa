package com.Microservicios.GestionMascotas.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.Microservicios.GestionMascotas.model.Especie;
import com.Microservicios.GestionMascotas.service.EspecieService;

@RestController
@CrossOrigin(origins = "*")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    @GetMapping("/api/v1/especies")
    public ResponseEntity<List<Especie>> listarEspecies() {
        List<Especie> lista = especieService.obtenerTodas();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }
    @PutMapping("/{id}")
    public Especie actualizarEspecie(@PathVariable Long id, @RequestBody Especie especie) {
    return especieService.actualizar(id, especie);
    }


    @GetMapping("/api/v1/especies/{id}")
    public ResponseEntity<Especie> obtenerEspecie(@PathVariable Long id) {
        return ResponseEntity.ok(especieService.obtenerPorId(id));
    }

    @PostMapping("/api/v1/especies")
    public ResponseEntity<Especie> crearEspecie(@RequestBody Especie especie) {
        return ResponseEntity.ok(especieService.guardar(especie));
    }

    @DeleteMapping("/api/v1/especies/{id}")
    public ResponseEntity<Void> eliminarEspecie(@PathVariable Long id) {
        especieService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
