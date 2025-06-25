package com.example.GestionDeServicios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GestionDeServicios.dto.ServicioRequest;
import com.example.GestionDeServicios.model.Servicio;
import com.example.GestionDeServicios.services.ServicioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @Operation(summary = "Obtener todos los servicios")
    @ApiResponse(responseCode = "200", description = "Lista de servicios",
            content = @Content(schema = @Schema(implementation = Servicio.class)))
    @GetMapping
    public List<Servicio> getAllServicios() {
        return servicioService.findAll();
    }

    @Operation(summary = "Obtener un servicio por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Servicio encontrado",
                content = @Content(schema = @Schema(implementation = Servicio.class))),
        @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    })
    @GetMapping("/{id}")
    public Servicio getServicioById(@PathVariable Long id) {
        return servicioService.findById(id);
    }

    @Operation(summary = "Crear un nuevo servicio")
    @ApiResponse(responseCode = "200", description = "Servicio creado exitosamente",
            content = @Content(schema = @Schema(implementation = Servicio.class)))
    @PostMapping
    public Servicio createServicio(@Valid @RequestBody ServicioRequest request) {
        return servicioService.save(request);
    }

    @Operation(summary = "Actualizar un servicio por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Servicio actualizado correctamente",
                content = @Content(schema = @Schema(implementation = Servicio.class))),
        @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable Long id, @Valid @RequestBody ServicioRequest request) {
        Servicio actualizado = servicioService.update(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar un servicio por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Servicio eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    })
    @DeleteMapping("/{id}")
    public void deleteServicioById(@PathVariable Long id) {
        servicioService.deleteById(id);
    }
}
