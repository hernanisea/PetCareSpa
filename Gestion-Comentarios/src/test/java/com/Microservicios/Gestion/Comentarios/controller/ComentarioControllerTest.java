package com.Microservicios.Gestion.Comentarios.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.Microservicios.Gestion.Comentarios.dto.ComentarioRequest;
import com.Microservicios.Gestion.Comentarios.model.Comentario;
import com.Microservicios.Gestion.Comentarios.service.ComentarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@WebMvcTest(ComentarioController.class)
public class ComentarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComentarioService comentarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarComentarios_deberiaRetornarLista() throws Exception {
        Comentario comentario = new Comentario(1L, "Buen servicio", "2024-06-01", true, 1L, 2L);
        when(comentarioService.obtenerComentarios()).thenReturn(List.of(comentario));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/comentarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].contenido").value("Buen servicio"));
    }

    @Test
    void crearComentario_deberiaRetornarComentarioGuardado() throws Exception {
        ComentarioRequest request = new ComentarioRequest();
        request.setContenido("Test nuevo");
        request.setFechaCreacion("2024-06-01");
        request.setEstado(true);
        request.setIdUsuario(1L);
        request.setIdReserva(1L);

        Comentario comentario = new Comentario(1L, "Test nuevo", "2024-06-01", true, 1L, 1L);
        when(comentarioService.crearComentario(any())).thenReturn(comentario);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/comentarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contenido").value("Test nuevo"))
                .andExpect(jsonPath("$.estado").value(true));
    }

    @Test
    void eliminarComentario_test() throws Exception {
        Long comentarioId= 1L;

        doNothing().when(comentarioService).eliminarComentario(comentarioId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/comentarios/{id}", comentarioId))
        .andExpect(status().isNoContent());

        verify(comentarioService, times(1)).eliminarComentario(comentarioId);
    }
}
