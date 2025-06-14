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

import com.example.GestionDeServicios.model.TipoServicio;
import com.example.GestionDeServicios.services.TipoServicioService;

@RestController
@RequestMapping("api/tiposervicios")
public class TipoServicioController {

    @Autowired
    private TipoServicioService tipoServicioService;

    @GetMapping
    public List<TipoServicio> getAllTipoServicios(){
        return tipoServicioService.findAll();
    }

    @GetMapping("/{id}")
    public TipoServicio getTipoServicioById(@PathVariable Long id){
        return tipoServicioService.findById(id);
    }

    @PostMapping
    public TipoServicio createTipoServicio(@RequestBody TipoServicio tipoServicio){
        return tipoServicioService.save(tipoServicio);
    }

    @DeleteMapping("/{id}")
    public void deleteTipoServicioById(@PathVariable Long id){
        tipoServicioService.deleteById(id);
    }
}