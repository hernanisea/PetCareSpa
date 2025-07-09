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

import com.Microservicios.Gestion.de.Direcciones.dto.DireccionRequest;
import com.Microservicios.Gestion.de.Direcciones.dto.DireccionRequestDto;
import com.Microservicios.Gestion.de.Direcciones.dto.DireccionResponse;
import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.service.DireccionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @Operation(summary = "Listar todas las direcciones")
    @ApiResponse(responseCode = "200", description = "Direcciones listadas",
            content = @Content(schema = @Schema(implementation = Direccion.class)))
    @GetMapping("/direcciones")
    public ResponseEntity<List<Direccion>> getAllDirecciones() {
        return ResponseEntity.ok(direccionService.findAll());
    }

    @Operation(summary = "Buscar dirección por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Dirección encontrada",
                content = @Content(schema = @Schema(implementation = Direccion.class))),
        @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    @GetMapping("/direcciones/{id}")
    public ResponseEntity<Direccion> getDireccionById(@PathVariable Long id) {
        return direccionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar direcciones por ID de usuario")
    @ApiResponse(responseCode = "200", description = "Direcciones del usuario listadas")
    @GetMapping("/usuarios/{usuarioId}/direcciones")
    public ResponseEntity<List<Direccion>> getDireccionesByUsuario(@PathVariable Long usuarioId) {
        List<Direccion> direcciones = direccionService.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(direcciones);
    }

    @Operation(summary = "Crear nueva dirección")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Dirección creada",
                content = @Content(schema = @Schema(implementation = Direccion.class))),
        @ApiResponse(responseCode = "400", description = "Usuario no válido")
    })
    @PostMapping("/direcciones")
    public ResponseEntity<DireccionResponse> createDireccion(@RequestBody DireccionRequest request) {
        Direccion direccion = direccionService.saveFromDto(request);
        DireccionResponse response = mapToResponse(direccion);
        return ResponseEntity.status(201).body(response);
    }

    private DireccionResponse mapToResponse(Direccion direccion) {
        DireccionResponse dto = new DireccionResponse();
        dto.setIdDireccion(direccion.getIdDireccion());
        dto.setCalle(direccion.getCalle());
        dto.setDescripcion(direccion.getDescripcion());
        dto.setCodigoPostal(direccion.getCodigoPostal());
        dto.setUsuarioId(direccion.getUsuarioId());
        dto.setComuna(direccion.getComuna().getNombre());
        dto.setRegion(direccion.getComuna().getRegion().getNombre());
        return dto;
    }

    @Operation(summary = "Eliminar dirección por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Dirección eliminada"),
        @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    @DeleteMapping("/direcciones/{id}")
    public ResponseEntity<Void> deleteDireccion(@PathVariable Long id) {
        direccionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
