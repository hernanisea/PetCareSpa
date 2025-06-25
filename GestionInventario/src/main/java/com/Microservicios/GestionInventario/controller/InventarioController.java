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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

    @Operation(summary = "Listar todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista de productos",
            content = @Content(schema = @Schema(implementation = Producto.class)))
    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @Operation(summary = "Obtener un producto por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
                content = @Content(schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable("id") Long idProducto) {
        return ResponseEntity.ok(productoService.obtenerPorId(idProducto));
    }

    @Operation(summary = "Crear un nuevo producto")
    @ApiResponse(responseCode = "200", description = "Producto creado exitosamente",
            content = @Content(schema = @Schema(implementation = Producto.class)))
    @PostMapping("/productos")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.guardar(producto));
    }

    @Operation(summary = "Actualizar un producto por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente",
                content = @Content(schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable("id") Long idProducto, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizar(idProducto, producto));
    }

    @Operation(summary = "Eliminar un producto por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Long idProducto) {
        productoService.eliminar(idProducto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener productos con stock bajo")
    @ApiResponse(responseCode = "200", description = "Productos con bajo stock encontrados")
    @GetMapping("/productos/stock-bajo")
    public List<Producto> obtenerProductosConStockBajo() {
        return productoService.obtenerStockBajo();
    }

    
    @Operation(summary = "Listar todos los tratamientos")
    @ApiResponse(responseCode = "200", description = "Lista de tratamientos",
            content = @Content(schema = @Schema(implementation = Tratamiento.class)))
    @GetMapping("/tratamientos")
    public ResponseEntity<List<Tratamiento>> listarTratamientos() {
        return ResponseEntity.ok(tratamientoService.listarTodos());
    }

    @Operation(summary = "Obtener un tratamiento por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tratamiento encontrado",
                content = @Content(schema = @Schema(implementation = Tratamiento.class))),
        @ApiResponse(responseCode = "404", description = "Tratamiento no encontrado")
    })
    @GetMapping("/tratamientos/{id}")
    public ResponseEntity<Tratamiento> obtenerTratamiento(@PathVariable("id") Long idProducto) {
        return ResponseEntity.ok(tratamientoService.obtenerPorId(idProducto));
    }

    @Operation(summary = "Crear un tratamiento asociado a un producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tratamiento creado exitosamente",
                content = @Content(schema = @Schema(implementation = Tratamiento.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o producto no encontrado")
    })
    @PostMapping("/tratamientos")
    public ResponseEntity<Tratamiento> crearTratamiento(@RequestBody @Validated TratamientoRequest request) {
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setCantidad(request.getCantidad());
        tratamiento.setSubtotal(request.getSubtotal());

        Tratamiento nuevo = tratamientoService.crearTratamientoConDto(tratamiento, request.getIdProducto());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @Operation(summary = "Eliminar un tratamiento por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tratamiento eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Tratamiento no encontrado")
    })
    @DeleteMapping("/tratamientos/{id}")
    public ResponseEntity<Void> eliminarTratamiento(@PathVariable("id") Long idProducto) {
        tratamientoService.eliminar(idProducto);
        return ResponseEntity.noContent().build();
    }
}
