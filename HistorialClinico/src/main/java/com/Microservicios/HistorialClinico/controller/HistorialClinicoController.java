package com.Microservicios.HistorialClinico.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Microservicios.HistorialClinico.dto.HistorialClinicoRequest;
import com.Microservicios.HistorialClinico.model.HistorialClinico;
import com.Microservicios.HistorialClinico.service.HistorialClinicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/historiales")
@CrossOrigin(origins = "*")
public class HistorialClinicoController {

    private final HistorialClinicoService historialClinicoService;

    public HistorialClinicoController(HistorialClinicoService historialClinicoService) {
        this.historialClinicoService = historialClinicoService;
    }

    @Operation(summary = "Listar todos los historiales clínicos")
    @ApiResponse(responseCode = "200", description = "Lista de historiales obtenida exitosamente",
            content = @Content(schema = @Schema(implementation = HistorialClinico.class)))
    @GetMapping
    public ResponseEntity<List<HistorialClinico>> listarHistoriales() {
        return ResponseEntity.ok(historialClinicoService.listarHistoriales());
    }

    @Operation(summary = "Obtener historial clínico por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Historial encontrado",
                content = @Content(schema = @Schema(implementation = HistorialClinico.class))),
        @ApiResponse(responseCode = "404", description = "Historial no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HistorialClinico> obtenerHistorial(@PathVariable Long id) {
        return historialClinicoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));
    }

    @Operation(summary = "Crear un nuevo historial clínico")
    @ApiResponse(responseCode = "201", description = "Historial creado exitosamente",
            content = @Content(schema = @Schema(implementation = HistorialClinico.class)))
    @PostMapping
    public ResponseEntity<HistorialClinico> crearHistorial(@RequestBody HistorialClinicoRequest request) {
        return ResponseEntity.ok(historialClinicoService.guardarHistorial(request));
    }

    @Operation(summary = "Actualizar un historial clínico existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Historial actualizado correctamente",
                content = @Content(schema = @Schema(implementation = HistorialClinico.class))),
        @ApiResponse(responseCode = "404", description = "Historial no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HistorialClinico> actualizarHistorial(@PathVariable Long id, @RequestBody HistorialClinicoRequest request) {
        return ResponseEntity.ok(historialClinicoService.actualizarHistorial(id, request));
    }

    @Operation(summary = "Eliminar un historial clínico por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Historial eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Historial no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistorial(@PathVariable Long id) {
        historialClinicoService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/usuario/{idUsuario}")
    public List<HistorialClinico> obtenerPorUsuario(@PathVariable Long idUsuario) {
        return historialClinicoService.obtenerPorUsuarioId(idUsuario);
    }

}
