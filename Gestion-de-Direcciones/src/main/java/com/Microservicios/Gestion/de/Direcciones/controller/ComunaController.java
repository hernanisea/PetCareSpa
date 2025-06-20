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

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.service.ComunaService;

@RestController
@RequestMapping("/api/v1/direccion/comunas")
public class ComunaController {

    private final ComunaService comunaService;

    public ComunaController(ComunaService comunaService) {
        this.comunaService = comunaService;
    }

    @GetMapping
    public List<Comuna> findAllComunas() {
        return comunaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comuna> findComunaById(@PathVariable Long id) {
        return comunaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Comuna saveComuna(@RequestBody Comuna comuna) {
        return comunaService.save(comuna);
    }

    @DeleteMapping("/{id}")
    public void deleteComuna(@PathVariable Long id) {
        comunaService.delete(id);
    }
}



