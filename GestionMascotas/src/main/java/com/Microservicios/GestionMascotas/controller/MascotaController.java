package com.Microservicios.GestionMascotas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Microservicios.GestionMascotas.model.Mascotas;
import com.Microservicios.GestionMascotas.service.MascotaService;

@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController {

     @Autowired
    private MascotaService mascotaService;

    // POST 
    @PostMapping("/mascota")
    public ResponseEntity<Mascotas> registrarMascota(@RequestBody Mascotas mascotas) {
        Mascotas nuevaMascota = mascotaService.agregarMascotas(mascotas);
        return ResponseEntity.status(201).body(nuevaMascota);
    }

    // POST /mascota/resgistro (simulado)
    @PostMapping("/registro")
    public ResponseEntity<String> login(@RequestBody Mascotas mascotas) {
        // Simulación de autenticación
        return ResponseEntity.ok("Registrado con exito");
    }

    // GET /usuarios/{id}
    @GetMapping("/{idMascota}")
    public ResponseEntity<Mascotas> obtenerUsuario(@PathVariable Integer idMascota) {
        Mascotas mascotas = mascotaService.obtenerMascotas(idMascota);
        if (mascotas != null) {
            return ResponseEntity.ok(mascotas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT /usuarios/{id}
    @PutMapping("/{idMascota}")
    public ResponseEntity<Mascotas> mascotaActualizado(@PathVariable Integer idMascota, @RequestBody Mascotas mascotaActualizada) {
            Mascotas mascotaActualizado = mascotaService.mascotaActualizado(idMascota, mascotaActualizada);
        if (mascotaActualizado != null) {
            return ResponseEntity.ok(mascotaActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /usuarios/{id}
    @DeleteMapping("/{idMascota}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer idMascota) {
        mascotaService.eliminarMascotas(idMascota);
        return ResponseEntity.noContent().build();
    }

    // GET /usuarios
    @GetMapping
    public ResponseEntity<List<Mascotas>> listarMascotas() {
        List<Mascotas> mascotas = mascotaService.listarMascotas();
        if (mascotas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mascotas);
    }
}
