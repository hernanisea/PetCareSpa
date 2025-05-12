package com.Microservicios.Gestion.de.Direcciones.controller;

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

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.service.ComunaService;
import com.Microservicios.Gestion.de.Direcciones.service.DireccionService;
import com.Microservicios.Gestion.de.Direcciones.service.RegionService;

@RestController
@RequestMapping("/api/v1/direccion")
public class DireccionGlobalController {
    @Autowired
    private final DireccionService direccionService;
    @Autowired
    private final ComunaService comunaService;
    @Autowired
    private final RegionService regionService;

    public DireccionGlobalController(DireccionService direccionService,
                                     ComunaService comunaService,
                                     RegionService regionService) {
        this.direccionService = direccionService;
        this.comunaService = comunaService;
        this.regionService = regionService;
    }

    // ---------------- DIRECCIONES ----------------

    @GetMapping("/direcciones")
    public List<Direccion> findAllDirecciones() {
        return direccionService.findAll();
    }

    @GetMapping("/direcciones/{id}")
    public ResponseEntity<Direccion> findDireccionById(@PathVariable Long id) {
        return direccionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/direcciones")
    public Direccion saveDireccion(@RequestBody Direccion direccion) {
        return direccionService.save(direccion);
    }

    @DeleteMapping("/direcciones/{id}")
    public void deleteDireccion(@PathVariable Long id) {
        direccionService.delete(id);
    }

    // ---------------- COMUNAS ----------------

    @GetMapping("/comunas")
    public List<Comuna> findAllComunas() {
        return comunaService.findAll();
    }

    @GetMapping("/comunas/{id}")
    public ResponseEntity<Comuna> findComunaById(@PathVariable Long id) {
        return comunaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/comunas")
    public Comuna saveComuna(@RequestBody Comuna comuna) {
        return comunaService.save(comuna);
    }

    @DeleteMapping("/comunas/{id}")
    public void deleteComuna(@PathVariable Long id) {
        comunaService.delete(id);
    }

    // ---------------- REGIONES ----------------

    @GetMapping("/regiones")
    public List<Region> findAllRegiones() {
        return regionService.findAll();
    }

    @GetMapping("/regiones/{id}")
    public ResponseEntity<Region> findRegionById(@PathVariable Long id) {
        return regionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/regiones")
    public Region saveRegion(@RequestBody Region region) {
        return regionService.save(region);
    }

    @DeleteMapping("/regiones/{id}")
    public void deleteRegion(@PathVariable Long id) {
        regionService.delete(id);
    }
}