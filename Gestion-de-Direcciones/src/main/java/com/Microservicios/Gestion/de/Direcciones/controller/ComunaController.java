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

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.service.ComunaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/direccion/comunas")
public class ComunaController {

    private final ComunaService comunaService;

    public ComunaController(ComunaService comunaService) {
        this.comunaService = comunaService;
    }

    @Operation(summary = "Listar todas las comunas")
    @ApiResponse(responseCode = "200", description = "Lista de comunas",
            content = @Content(schema = @Schema(implementation = Comuna.class)))
    @GetMapping
    public List<Comuna> findAllComunas() {
        return comunaService.findAll();
    }

    @Operation(summary = "Obtener una comuna por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Comuna encontrada",
                content = @Content(schema = @Schema(implementation = Comuna.class))),
        @ApiResponse(responseCode = "404", description = "Comuna no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Comuna> findComunaById(@PathVariable Long id) {
        return comunaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva comuna")
    @ApiResponse(responseCode = "200", description = "Comuna creada exitosamente",
            content = @Content(schema = @Schema(implementation = Comuna.class)))
    @PostMapping
    public Comuna saveComuna(@RequestBody Comuna comuna) {
        return comunaService.save(comuna);
    }

    @Operation(summary = "Eliminar una comuna por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Comuna eliminada"),
        @ApiResponse(responseCode = "404", description = "Comuna no encontrada")
    })
    @DeleteMapping("/{id}")
    public void deleteComuna(@PathVariable Long id) {
        comunaService.delete(id);
    }
}
