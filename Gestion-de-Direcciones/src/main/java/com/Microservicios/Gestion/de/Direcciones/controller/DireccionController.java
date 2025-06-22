package com.Microservicios.Gestion.de.Direcciones.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.service.DireccionService;

@RestController
@RequestMapping("/api/v1/direccion/direcciones")
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping
    public List<Direccion> findAllDirecciones() {
        return direccionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> findDireccionById(@PathVariable Long id) {
        return direccionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Direccion> getDireccionesByUsuarioId(@PathVariable Long usuarioId) {
        return direccionService.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public Direccion saveDireccion(@RequestBody Direccion direccion) {
        return direccionService.save(direccion);
    }

    @DeleteMapping("/{id}")
    public void deleteDireccion(@PathVariable Long id) {
        direccionService.delete(id);
    }
}
