package com.Microservicios.Gestion.Comentarios.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Microservicios.Gestion.Comentarios.dto.ComentarioRequest;
import com.Microservicios.Gestion.Comentarios.model.Comentario;
import com.Microservicios.Gestion.Comentarios.repository.ComentarioRepository;
import com.Microservicios.Gestion.Comentarios.webclient.UsuarioClient;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioClient usuarioClient;

    public ComentarioService(ComentarioRepository comentarioRepository, UsuarioClient usuarioClient) {
        this.comentarioRepository = comentarioRepository;
        this.usuarioClient = usuarioClient;
    }

    public List<Comentario> obtenerComentarios() {
        return comentarioRepository.findAll();
    }

    public Comentario obtenerComentarioPorId(Long id) {
        return comentarioRepository.findById(id).orElseThrow();
    }

    public Comentario crearComentario(ComentarioRequest request) {
        Comentario nuevo = new Comentario(null, request.getContenido(), request.getFechaCreacion(),
                                           request.getEstado(), request.getIdUsuario(), request.getIdReserva());
        return comentarioRepository.save(nuevo);
    }

    public Comentario actualizarComentario(Long id, ComentarioRequest request) {
        Comentario comentario = obtenerComentarioPorId(id);
        comentario.setContenido(request.getContenido());
        comentario.setFechaCreacion(request.getFechaCreacion());
        comentario.setEstado(request.getEstado());
        comentario.setIdUsuario(request.getIdUsuario());
        comentario.setIdReserva(request.getIdReserva());
        return comentarioRepository.save(comentario);
    }

    public void eliminarComentario(Long id) {
        comentarioRepository.deleteById(id);
    }

    public List<Comentario> obtenerPorUsuario(Long idUsuario) {
        return comentarioRepository.findByIdUsuario(idUsuario);
    }

    public Map<String, Object> obtenerConUsuario(Long idUsuario) {
        Map<String, Object> usuario = usuarioClient.obtenerUsuarioPorId(idUsuario);
        List<Comentario> comentarios = comentarioRepository.findByIdUsuario(idUsuario);
        return Map.of(
                "usuario", Map.of(
                    "nombre", usuario.get("nombre"),
                    "correo", usuario.get("correo")
                ),
                "comentarios", comentarios
        );
    }
}
