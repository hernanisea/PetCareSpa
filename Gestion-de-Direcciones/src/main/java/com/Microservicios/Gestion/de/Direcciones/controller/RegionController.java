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

import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.service.RegionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/direccion/regiones")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @Operation(summary = "Listar todas las regiones")
    @ApiResponse(responseCode = "200", description = "Lista de regiones",
            content = @Content(schema = @Schema(implementation = Region.class)))
    @GetMapping
    public List<Region> findAllRegiones() {
        return regionService.findAll();
    }

    @Operation(summary = "Obtener una región por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Región encontrada",
                content = @Content(schema = @Schema(implementation = Region.class))),
        @ApiResponse(responseCode = "404", description = "Región no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Region> findRegionById(@PathVariable Long id) {
        return regionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva región")
    @ApiResponse(responseCode = "200", description = "Región creada",
            content = @Content(schema = @Schema(implementation = Region.class)))
    @PostMapping
    public Region saveRegion(@RequestBody Region region) {
        return regionService.save(region);
    }

    @Operation(summary = "Eliminar una región por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Región eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Región no encontrada")
    })
    @DeleteMapping("/{id}")
    public void deleteRegion(@PathVariable Long id) {
        regionService.delete(id);
    }
}
