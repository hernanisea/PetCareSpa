package com.Microservicios.GestionMascotas.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Microservicios.GestionMascotas.model.Mascotas;
import com.Microservicios.GestionMascotas.service.MascotasService;

@RestController
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    private MascotasService mascotasService;

    @GetMapping("/api/v1/mascotas")
    public ResponseEntity<List<Mascotas>> obtenerTodas() {
        return ResponseEntity.ok(mascotasService.obtenerMascotas());
    }

    @GetMapping("/api/v1/mascotas/{id}")
    public ResponseEntity<Mascotas> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mascotasService.obtenerMascotaPorId(id));
    }

    @GetMapping("/api/v1/mascotas/usuario/{idUsuario}")
    public ResponseEntity<List<Mascotas>> obtenerPorUsuario(@PathVariable Long idUsuario) {
        List<Mascotas> mascotas = mascotasService.obtenerPorUsuario(idUsuario);
        if (mascotas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/api/v1/mascotas/especie/{idEspecie}")
    public ResponseEntity<List<Mascotas>> obtenerPorEspecie(@PathVariable Long idEspecie) {
        List<Mascotas> mascotas = mascotasService.obtenerPorEspecie(idEspecie);
        if (mascotas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/api/v1/mascotas/raza/{idRaza}")
    public ResponseEntity<List<Mascotas>> obtenerPorRaza(@PathVariable Long idRaza) {
        List<Mascotas> mascotas = mascotasService.obtenerPorRaza(idRaza);
        if (mascotas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mascotas);
    }

    @PostMapping("/api/v1/mascotas")
    public ResponseEntity<Mascotas> crearMascota(
            @RequestParam Long idUsuario,
            @RequestParam String nombre,
            @RequestParam Integer edad,
            @RequestParam String sexo,
            @RequestParam Integer pesoKg,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaRegistro,
            @RequestParam Long idEspecie,
            @RequestParam Long idRaza,
            @RequestParam(required = false) Long idReserva
    ) {
        return ResponseEntity.ok(mascotasService.crearMascota(idUsuario, nombre, edad, sexo, pesoKg, fechaRegistro, idEspecie, idRaza, idReserva));
    }

    @PutMapping("/api/v1/mascotas/{id}")
    public ResponseEntity<Mascotas> actualizarMascota(
            @PathVariable Long id,
            @RequestParam Long idUsuario,
            @RequestParam String nombre,
            @RequestParam Integer edad,
            @RequestParam String sexo,
            @RequestParam Integer pesoKg,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaRegistro,
            @RequestParam Long idEspecie,
            @RequestParam Long idRaza,
            @RequestParam(required = false) Long idReserva
    ) {
        return ResponseEntity.ok(mascotasService.actualizarMascota(id, idUsuario, nombre, edad, sexo, pesoKg, fechaRegistro, idEspecie, idRaza, idReserva));
    }

    @DeleteMapping("/api/v1/mascotas/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotasService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
    }
}
