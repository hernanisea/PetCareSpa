package com.Microservicios.GestionInventario.controller;

import com.Microservicios.GestionInventario.model.Producto;
import com.Microservicios.GestionInventario.model.Tratamiento;
import com.Microservicios.GestionInventario.service.ProductoService;
import com.Microservicios.GestionInventario.service.TratamientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // -------------------
    // Endpoints Producto
    // -------------------

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @PostMapping("/productos")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.guardar(producto));
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ----------------------
    // Endpoints Tratamiento
    // ----------------------

    @GetMapping("/tratamientos")
    public ResponseEntity<List<Tratamiento>> listarTratamientos() {
        return ResponseEntity.ok(tratamientoService.listarTodos());
    }

    @GetMapping("/tratamientos/{id}")
    public ResponseEntity<Tratamiento> obtenerTratamiento(@PathVariable Long id) {
        return ResponseEntity.ok(tratamientoService.obtenerPorId(id));
    }

    @PostMapping("/tratamientos")
    public ResponseEntity<Tratamiento> crearTratamiento(@RequestBody TratamientoRequest request) {
        Tratamiento nuevo = new Tratamiento(null, request.getCantidad(), request.getSubtotal(), null);
        return ResponseEntity.ok(tratamientoService.guardar(nuevo, request.getIdProducto()));
    }

    @DeleteMapping("/tratamientos/{id}")
    public ResponseEntity<Void> eliminarTratamiento(@PathVariable Long id) {
        tratamientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // DTO interno para el POST de tratamientos
    public static class TratamientoRequest {
        private Integer cantidad;
        private Double subtotal;
        private Long idProducto;

        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

        public Double getSubtotal() { return subtotal; }
        public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

        public Long getIdProducto() { return idProducto; }
        public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }
    }
}
