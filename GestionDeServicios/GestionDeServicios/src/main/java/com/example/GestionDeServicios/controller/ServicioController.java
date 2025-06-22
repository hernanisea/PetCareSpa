package com.example.GestionDeServicios.controller;

import com.example.GestionDeServicios.dto.ServicioRequest;
import com.example.GestionDeServicios.model.Servicio;
import com.example.GestionDeServicios.services.ServicioService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> getAllServicios() {
        return servicioService.findAll();
    }

    @GetMapping("/{id}")
    public Servicio getServicioById(@PathVariable Long id) {
        return servicioService.findById(id);
    }

    @PostMapping
    public Servicio createServicio(@Valid @RequestBody ServicioRequest request) {
        return servicioService.save(request);
}


    @PutMapping("/{id}")
public ResponseEntity<Servicio> updateServicio(@PathVariable Long id, @Valid @RequestBody ServicioRequest request) {
    Servicio actualizado = servicioService.update(id, request);
    return ResponseEntity.ok(actualizado);
}


    @DeleteMapping("/{id}")
    public void deleteServicioById(@PathVariable Long id) {
        servicioService.deleteById(id);
    }
} 
