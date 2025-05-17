package com.Microservicios.Gestion.Comentarios.controller;

import com.Microservicios.Gestion.Comentarios.model.Comentario;
import com.Microservicios.Gestion.Comentarios.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
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
        Comentario nuevo = comentarioService.crearComentario(
                request.getContenido(),
                request.getFechaCreacion(),
                request.getEstado(),
                request.getIdUsuario(),
                request.getIdReserva()
        );
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentario(@PathVariable Long id, @RequestBody ComentarioRequest request) {
        Comentario actualizado = comentarioService.actualizarComentario(
                id,
                request.getContenido(),
                request.getFechaCreacion(),
                request.getEstado(),
                request.getIdUsuario(),
                request.getIdReserva()
        );
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }

    // DTO de entrada
    public static class ComentarioRequest {
        private String contenido;
        private String fechaCreacion;
        private Boolean estado;
        private Long idUsuario;
        private Long idReserva;

        public String getContenido() { return contenido; }
        public void setContenido(String contenido) { this.contenido = contenido; }

        public String getFechaCreacion() { return fechaCreacion; }
        public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

        public Boolean getEstado() { return estado; }
        public void setEstado(Boolean estado) { this.estado = estado; }

        public Long getIdUsuario() { return idUsuario; }
        public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

        public Long getIdReserva() { return idReserva; }
        public void setIdReserva(Long idReserva) { this.idReserva = idReserva; }
    }
}
