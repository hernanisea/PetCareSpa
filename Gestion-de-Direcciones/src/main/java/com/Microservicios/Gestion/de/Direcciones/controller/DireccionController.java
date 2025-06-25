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

import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.service.DireccionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/direccion/direcciones")
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @Operation(summary = "Listar todas las direcciones")
    @ApiResponse(responseCode = "200", description = "Lista de direcciones",
            content = @Content(schema = @Schema(implementation = Direccion.class)))
    @GetMapping
    public List<Direccion> findAllDirecciones() {
        return direccionService.findAll();
    }

    @Operation(summary = "Obtener una dirección por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Dirección encontrada",
                content = @Content(schema = @Schema(implementation = Direccion.class))),
        @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Direccion> findDireccionById(@PathVariable Long id) {
        return direccionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener direcciones por ID de usuario")
    @ApiResponse(responseCode = "200", description = "Direcciones encontradas para el usuario")
    @GetMapping("/usuario/{usuarioId}")
    public List<Direccion> getDireccionesByUsuarioId(@PathVariable Long usuarioId) {
        return direccionService.findByUsuarioId(usuarioId);
    }

    @Operation(summary = "Crear una nueva dirección")
    @ApiResponse(responseCode = "200", description = "Dirección creada",
            content = @Content(schema = @Schema(implementation = Direccion.class)))
    @PostMapping
    public Direccion saveDireccion(@RequestBody Direccion direccion) {
        return direccionService.save(direccion);
    }

    @Operation(summary = "Eliminar una dirección por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Dirección eliminada"),
        @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    @DeleteMapping("/{id}")
    public void deleteDireccion(@PathVariable Long id) {
        direccionService.delete(id);
    }
}

