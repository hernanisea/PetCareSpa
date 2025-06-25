package com.Microservicios.Gestion.Comentarios.controller;

import com.Microservicios.Gestion.Comentarios.dto.ComentarioRequest;
import com.Microservicios.Gestion.Comentarios.model.Comentario;
import com.Microservicios.Gestion.Comentarios.service.ComentarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @Operation(summary = "Listar todos los comentarios")
    @ApiResponse(responseCode = "200", description = "Lista de comentarios",
            content = @Content(schema = @Schema(implementation = Comentario.class)))
    @GetMapping
    public ResponseEntity<List<Comentario>> listarComentarios() {
        return ResponseEntity.ok(comentarioService.obtenerComentarios());
    }

    @Operation(summary = "Obtener un comentario por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Comentario encontrado",
                content = @Content(schema = @Schema(implementation = Comentario.class))),
        @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obtenerComentario(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.obtenerComentarioPorId(id));
    }

    @Operation(summary = "Crear un nuevo comentario")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Comentario creado",
                content = @Content(schema = @Schema(implementation = Comentario.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o usuario inexistente")
    })
    @PostMapping
    public ResponseEntity<Comentario> crearComentario(@RequestBody ComentarioRequest request) {
        return ResponseEntity.ok(comentarioService.crearComentario(request));
    }

    @Operation(summary = "Actualizar un comentario existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Comentario actualizado",
                content = @Content(schema = @Schema(implementation = Comentario.class))),
        @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentario(@PathVariable Long id, @RequestBody ComentarioRequest request) {
        return ResponseEntity.ok(comentarioService.actualizarComentario(id, request));
    }

    @Operation(summary = "Eliminar un comentario por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Comentario eliminado"),
        @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener todos los comentarios de un usuario por su ID")
    @ApiResponse(responseCode = "200", description = "Lista de comentarios del usuario",
            content = @Content(schema = @Schema(implementation = Comentario.class)))
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(comentarioService.obtenerPorUsuario(idUsuario));
    }

    @Operation(summary = "Obtener comentarios con detalle de usuario")
    @ApiResponse(responseCode = "200", description = "Comentarios con información del usuario")
    @GetMapping("/usuario-detalle/{idUsuario}")
    public ResponseEntity<Map<String, Object>> obtenerComentariosConUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(comentarioService.obtenerConUsuario(idUsuario));
    }
}
