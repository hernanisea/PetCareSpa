package com.Microservicios.GestionMascotas.controller;

import com.Microservicios.GestionMascotas.model.Especie;
import com.Microservicios.GestionMascotas.service.EspecieService;
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
@CrossOrigin(origins = "*")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    @Operation(summary = "Listar todas las especies")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Especies listadas",
                content = @Content(schema = @Schema(implementation = Especie.class))),
        @ApiResponse(responseCode = "204", description = "No hay especies registradas")
    })
    @GetMapping("/api/v1/especies")
    public ResponseEntity<List<Especie>> listarEspecies() {
        List<Especie> lista = especieService.obtenerTodas();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener una especie por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Especie encontrada",
                content = @Content(schema = @Schema(implementation = Especie.class))),
        @ApiResponse(responseCode = "404", description = "Especie no encontrada")
    })
    @GetMapping("/api/v1/especies/{id}")
    public ResponseEntity<Especie> obtenerEspecie(@PathVariable Long id) {
        return ResponseEntity.ok(especieService.obtenerPorId(id));
    }

    @Operation(summary = "Crear una nueva especie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Especie creada exitosamente",
                content = @Content(schema = @Schema(implementation = Especie.class)))
    })
    @PostMapping("/api/v1/especies")
    public ResponseEntity<Especie> crearEspecie(@RequestBody Especie especie) {
        return ResponseEntity.ok(especieService.guardar(especie));
    }

    @Operation(summary = "Actualizar una especie por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Especie actualizada correctamente",
                content = @Content(schema = @Schema(implementation = Especie.class)))
    })
    @PutMapping("/api/v1/especies/{id}")
    public Especie actualizarEspecie(@PathVariable Long id, @RequestBody Especie especie) {
        return especieService.actualizar(id, especie);
    }

    @Operation(summary = "Eliminar una especie por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Especie eliminada correctamente")
    })
    @DeleteMapping("/api/v1/especies/{id}")
    public ResponseEntity<Void> eliminarEspecie(@PathVariable Long id) {
        especieService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
