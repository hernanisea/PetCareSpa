package com.Microservicios.GestionInventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Microservicios.GestionInventario.model.Inventario;
import com.Microservicios.GestionInventario.service.InventarioService;

@RestController
@RequestMapping("/api/v1/Inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> listarInventario() {
        List<Inventario> lista = inventarioService.listarInventario();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Inventario> guardarInventario(@RequestBody Inventario inventario) {
        Inventario nuevo = inventarioService.agregarInventario(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

  
    @PutMapping("/{idProducto}")
    public ResponseEntity<Inventario> inventarioActualizado(@PathVariable Long idProducto, @RequestBody Inventario inventario) {
        Inventario actualizado = inventarioService.inventarioActualizado(idProducto, inventario);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
}