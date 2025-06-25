package com.Microservicios.GestionMascotas.controller;

import com.Microservicios.GestionMascotas.dto.MascotaRequestDto;
import com.Microservicios.GestionMascotas.dto.MascotaResponseDto;
import com.Microservicios.GestionMascotas.service.MascotasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    private final MascotasService mascotasService;

    public MascotaController(MascotasService mascotasService) {
        this.mascotasService = mascotasService;
    }

    @Operation(summary = "Crear una nueva mascota")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Mascota creada exitosamente",
                content = @Content(schema = @Schema(implementation = MascotaResponseDto.class)))
    })
    @PostMapping
    public ResponseEntity<MascotaResponseDto> crearMascota(@RequestBody MascotaRequestDto request) {
        MascotaResponseDto mascota = mascotasService.crearMascota(request);
        return new ResponseEntity<>(mascota, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener mascotas por ID de usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mascotas encontradas",
                content = @Content(schema = @Schema(implementation = MascotaResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<MascotaResponseDto>> obtenerMascotasPorUsuario(@RequestParam(required = false) Long usuarioId) {
        List<MascotaResponseDto> mascotas = mascotasService.obtenerPorUsuario(usuarioId);
        return ResponseEntity.ok(mascotas);
    }

    @Operation(summary = "Obtener una mascota por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mascota encontrada",
                content = @Content(schema = @Schema(implementation = MascotaResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mascotasService.obtenerPorId(id));
    }

    @Operation(summary = "Actualizar una mascota por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mascota actualizada correctamente",
                content = @Content(schema = @Schema(implementation = MascotaResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MascotaResponseDto> actualizarMascota(@PathVariable Long id, @RequestBody MascotaRequestDto request) {
        MascotaResponseDto mascota = mascotasService.actualizarMascota(id, request);
        return ResponseEntity.ok(mascota);
    }

    @Operation(summary = "Eliminar una mascota por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Mascota eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotasService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
    }
}
