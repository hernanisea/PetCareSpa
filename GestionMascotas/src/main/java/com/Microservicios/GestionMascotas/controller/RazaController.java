package com.Microservicios.GestionMascotas.controller;

import com.Microservicios.GestionMascotas.model.Raza;
import com.Microservicios.GestionMascotas.service.RazaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/razas")
@CrossOrigin(origins = "*")
public class RazaController {

    @Autowired
    private RazaService razaService;

    @Operation(summary = "Listar todas las razas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Razas listadas",
                content = @Content(schema = @Schema(implementation = Raza.class))),
        @ApiResponse(responseCode = "204", description = "No hay razas registradas")
    })
    @GetMapping
    public ResponseEntity<List<Raza>> listarRazas() {
        List<Raza> lista = razaService.obtenerTodas();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener una raza por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Raza encontrada",
                content = @Content(schema = @Schema(implementation = Raza.class))),
        @ApiResponse(responseCode = "404", description = "Raza no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Raza> obtenerRaza(@PathVariable Long id) {
        return ResponseEntity.ok(razaService.obtenerPorId(id));
    }

    @Operation(summary = "Actualizar una raza por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Raza actualizada correctamente",
                content = @Content(schema = @Schema(implementation = Raza.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Raza> actualizarRaza(@PathVariable Long id, @RequestBody Raza raza) {
        Raza actualizada = razaService.actualizar(id, raza);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Crear una nueva raza")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Raza creada exitosamente",
                content = @Content(schema = @Schema(implementation = Raza.class)))
    })
    @PostMapping
    public ResponseEntity<Raza> crearRaza(@RequestBody Raza raza) {
        return ResponseEntity.ok(razaService.guardar(raza));
    }

    @Operation(summary = "Eliminar una raza por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Raza eliminada correctamente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRaza(@PathVariable Long id) {
        razaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
