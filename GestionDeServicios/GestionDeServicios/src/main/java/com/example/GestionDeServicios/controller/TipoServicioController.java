package com.example.GestionDeServicios.controller;

import com.example.GestionDeServicios.dto.TipoServicioRequest;
import com.example.GestionDeServicios.model.TipoServicio;
import com.example.GestionDeServicios.services.TipoServicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tiposervicios")
@CrossOrigin(origins = "*")
public class TipoServicioController {

    private final TipoServicioService tipoServicioService;

    public TipoServicioController(TipoServicioService tipoServicioService) {
        this.tipoServicioService = tipoServicioService;
    }

    @Operation(summary = "Obtener todos los tipos de servicios")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de tipos de servicio encontrada",
                content = @Content(schema = @Schema(implementation = TipoServicio.class))),
        @ApiResponse(responseCode = "204", description = "No hay tipos de servicio registrados")
    })
    @GetMapping
    public ResponseEntity<List<TipoServicio>> getAllTipoServicios() {
        List<TipoServicio> lista = tipoServicioService.findAll();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener un tipo de servicio por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de servicio encontrado",
                content = @Content(schema = @Schema(implementation = TipoServicio.class))),
        @ApiResponse(responseCode = "404", description = "Tipo de servicio no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getTipoServicioById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tipoServicioService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Operation(summary = "Crear un nuevo tipo de servicio")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tipo de servicio creado exitosamente",
                content = @Content(schema = @Schema(implementation = TipoServicio.class))),
        @ApiResponse(responseCode = "400", description = "Error de validación o datos inválidos")
    })
    @PostMapping
    public ResponseEntity<?> createTipoServicio(@RequestBody TipoServicioRequest request) {
        try {
            TipoServicio creado = tipoServicioService.save(request.getNombre());
            return ResponseEntity.status(201).body(creado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear tipo de servicio: " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un tipo de servicio por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tipo de servicio eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Tipo de servicio no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTipoServicioById(@PathVariable Long id) {
        try {
            tipoServicioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
