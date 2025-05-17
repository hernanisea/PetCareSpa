package com.Microservicios.Gestion.Comentarios.service;

import com.Microservicios.Gestion.Comentarios.model.Comentario;
import com.Microservicios.Gestion.Comentarios.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> obtenerComentarios() {
        return comentarioRepository.findAll();
    }

    public Comentario obtenerComentarioPorId(Long id) {
        return comentarioRepository.findById(id).orElseThrow();
    }

    public Comentario crearComentario(String contenido, String fechaCreacion, Boolean estado, Long idUsuario, Long idReserva) {
        Comentario nuevo = new Comentario(null, contenido, fechaCreacion, estado, idUsuario, idReserva);
        return comentarioRepository.save(nuevo);
    }

    public Comentario actualizarComentario(Long id, String contenido, String fechaCreacion, Boolean estado, Long idUsuario, Long idReserva) {
        Comentario comentario = obtenerComentarioPorId(id);
        comentario.setContenido(contenido);
        comentario.setFechaCreacion(fechaCreacion);
        comentario.setEstado(estado);
        comentario.setIdUsuario(idUsuario);
        comentario.setIdReserva(idReserva);
        return comentarioRepository.save(comentario);
    }

    public void eliminarComentario(Long id) {
        comentarioRepository.deleteById(id);
    }
}
