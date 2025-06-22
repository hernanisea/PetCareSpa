package com.Microservicios.GestionInventario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Microservicios.GestionInventario.dto.TratamientoRequest;
import com.Microservicios.GestionInventario.model.Producto;
import com.Microservicios.GestionInventario.model.Tratamiento;
import com.Microservicios.GestionInventario.service.ProductoService;
import com.Microservicios.GestionInventario.service.TratamientoService;

@RestController
@RequestMapping("/api/v1/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {

    private final ProductoService productoService;
    private final TratamientoService tratamientoService;

    public InventarioController(ProductoService productoService, TratamientoService tratamientoService) {
        this.productoService = productoService;
        this.tratamientoService = tratamientoService;
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable("id") Long idProducto) {
        return ResponseEntity.ok(productoService.obtenerPorId(idProducto));
    }

    @PostMapping("/productos")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.guardar(producto));
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable("id") Long idProducto, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizar(idProducto, producto));
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Long idProducto) {
        productoService.eliminar(idProducto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tratamientos")
    public ResponseEntity<List<Tratamiento>> listarTratamientos() {
        return ResponseEntity.ok(tratamientoService.listarTodos());
    }

    @GetMapping("/tratamientos/{id}")
    public ResponseEntity<Tratamiento> obtenerTratamiento(@PathVariable("id") Long idProducto) {
        return ResponseEntity.ok(tratamientoService.obtenerPorId(idProducto));
    }

    @GetMapping("/productos/stock-bajo")
    public List<Producto> obtenerProductosConStockBajo() {
        return productoService.obtenerStockBajo();
    }

    @PostMapping("/tratamientos")
    public ResponseEntity<Tratamiento> crearTratamiento(@RequestBody @Validated TratamientoRequest request) {
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setCantidad(request.getCantidad());
        tratamiento.setSubtotal(request.getSubtotal());

        Tratamiento nuevo = tratamientoService.crearTratamientoConDto(tratamiento, request.getIdProducto());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/tratamientos/{id}")
    public ResponseEntity<Void> eliminarTratamiento(@PathVariable("id") Long idProducto) {
        tratamientoService.eliminar(idProducto);
        return ResponseEntity.noContent().build();
    }
}