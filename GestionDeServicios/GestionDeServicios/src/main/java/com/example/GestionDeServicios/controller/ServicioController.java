package com.example.GestionDeServicios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GestionDeServicios.model.Servicio;
import com.example.GestionDeServicios.services.ServicioService;


@RestController
@RequestMapping("api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> getAllServicios(){
        return servicioService.findAll();
    }

    @GetMapping("/{id}")
    public Servicio getServicioById(@PathVariable Long id){
        return servicioService.findById(id);
    }

    @PostMapping
    public Servicio createTipoSala(@RequestBody Servicio servicio){
        return servicioService.save(servicio);
    }

    @DeleteMapping("/{id}")
    public void deleteTipoSalaById(@PathVariable Long id){
        servicioService.deleteById(id);
    }
}