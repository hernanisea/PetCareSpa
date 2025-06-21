package com.Microservicios.Gestion.Comentarios.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Microservicios.Gestion.Comentarios.dto.ComentarioRequest;
import com.Microservicios.Gestion.Comentarios.model.Comentario;
import com.Microservicios.Gestion.Comentarios.service.ComentarioService;

@RestController
@RequestMapping("/api/v1/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public ResponseEntity<List<Comentario>> listarComentarios() {
        return ResponseEntity.ok(comentarioService.obtenerComentarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obtenerComentario(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.obtenerComentarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<Comentario> crearComentario(@RequestBody ComentarioRequest request) {
        return ResponseEntity.ok(comentarioService.crearComentario(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentario(@PathVariable Long id, @RequestBody ComentarioRequest request) {
        return ResponseEntity.ok(comentarioService.actualizarComentario(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(comentarioService.obtenerPorUsuario(idUsuario));
    }

    @GetMapping("/usuario-detalle/{idUsuario}")
    public ResponseEntity<Map<String, Object>> obtenerComentariosConUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(comentarioService.obtenerConUsuario(idUsuario));
    }
}
