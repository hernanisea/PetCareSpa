package com.Microservicios.GestionMascotas.controller;

import com.Microservicios.GestionMascotas.dto.MascotaRequestDto;
import com.Microservicios.GestionMascotas.dto.MascotaResponseDto;
import com.Microservicios.GestionMascotas.service.MascotasService;
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

    @PostMapping
    public ResponseEntity<MascotaResponseDto> crearMascota(@RequestBody MascotaRequestDto request) {
        MascotaResponseDto mascota = mascotasService.crearMascota(request);
        return new ResponseEntity<>(mascota, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MascotaResponseDto>> obtenerMascotasPorUsuario(@RequestParam(required = false) Long usuarioId) {
        List<MascotaResponseDto> mascotas = mascotasService.obtenerPorUsuario(usuarioId);
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mascotasService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaResponseDto> actualizarMascota(@PathVariable Long id, @RequestBody MascotaRequestDto request) {
        MascotaResponseDto mascota = mascotasService.actualizarMascota(id, request);
        return ResponseEntity.ok(mascota);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotasService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
    }
}
