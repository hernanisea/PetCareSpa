package com.Microservicios.Gestion.Comentarios.service;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.Microservicios.Gestion.Comentarios.dto.ComentarioRequest;
import com.Microservicios.Gestion.Comentarios.model.Comentario;
import com.Microservicios.Gestion.Comentarios.repository.ComentarioRepository;
import com.Microservicios.Gestion.Comentarios.webclient.UsuarioClient;

public class ComentarioServiceTest {

    private ComentarioRepository comentarioRepository;
    private UsuarioClient usuarioClient;
    private ComentarioService comentarioService;

    @BeforeEach
    void setUp() {
        comentarioRepository = mock(ComentarioRepository.class);
        usuarioClient = mock(UsuarioClient.class);
        comentarioService = new ComentarioService(comentarioRepository, usuarioClient);
    }

    @Test
    void crearComentario_deberiaGuardarComentario() {
        ComentarioRequest request = new ComentarioRequest();
        request.setContenido("Contenido test");
        request.setFechaCreacion("2024-06-01");
        request.setEstado(true);
        request.setIdUsuario(1L);
        request.setIdReserva(2L);

        when(usuarioClient.obtenerUsuarioPorId(1L)).thenReturn(Map.of("id", 1L));
        when(comentarioRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Comentario resultado = comentarioService.crearComentario(request);

        assertThat(resultado.getContenido()).isEqualTo("Contenido test");
        assertThat(resultado.getEstado()).isTrue();
    }

    @Test
    void obtenerPorUsuario_deberiaRetornarComentarios() {
        Comentario comentario = new Comentario(1L, "Texto", "2024-06-01", true, 1L, 1L);
        when(comentarioRepository.findByIdUsuario(1L)).thenReturn(List.of(comentario));

        List<Comentario> comentarios = comentarioService.obtenerPorUsuario(1L);
        assertThat(comentarios).hasSize(1);
        assertThat(comentarios.get(0).getIdUsuario()).isEqualTo(1L);
    }
}
