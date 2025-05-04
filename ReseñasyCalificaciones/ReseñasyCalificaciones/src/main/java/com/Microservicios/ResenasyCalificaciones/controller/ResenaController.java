package com.Microservicios.ResenasyCalificaciones.controller;

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

import com.Microservicios.ResenasyCalificaciones.model.Resena;
import com.Microservicios.ResenasyCalificaciones.service.ResenaService;

@RestController
@RequestMapping("/api/v1/resena")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @PostMapping("/agregar")
    public ResponseEntity<Resena> guardarResena(@RequestBody Resena resena) {
        Resena nuevaResena = resenaService.agregarResena(resena);
        return ResponseEntity.status(201).body(nuevaResena);
    }

	
    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtenerResena(@PathVariable Long id) {
        Resena resena = resenaService.obtenerResena(id);
        if (resena != null) {
            return ResponseEntity.ok(resena);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Resena>> listaResena() {
        List<Resena> resena = resenaService.listaResena();
        if (resena.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resena);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResena(@PathVariable long id) {
        resenaService.eliminarResena(id);
        return ResponseEntity.noContent().build();
    }
}