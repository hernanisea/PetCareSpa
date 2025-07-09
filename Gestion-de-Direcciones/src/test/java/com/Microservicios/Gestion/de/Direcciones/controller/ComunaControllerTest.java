package com.Microservicios.Gestion.de.Direcciones.controller;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.service.ComunaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ComunaController.class)
public class ComunaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComunaService comunaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void obtenerTodasLasComunas() throws Exception {
        when(comunaService.findAll()).thenReturn(List.of(
                new Comuna(1L, "Santiago", new Region(1L, "RM")),
                new Comuna(2L, "Providencia", new Region(1L, "RM"))
        ));

        mockMvc.perform(get("/api/v1/direccion/comunas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void obtenerComunaPorId() throws Exception {
        Comuna comuna = new Comuna(1L, "Viña del Mar", new Region(2L, "Valparaíso"));
        when(comunaService.findById(1L)).thenReturn(Optional.of(comuna));

        mockMvc.perform(get("/api/v1/direccion/comunas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Viña del Mar"));
    }

    @Test
    void crearComuna() throws Exception {
        Comuna comuna = new Comuna(null, "Talca", new Region(3L, "Maule"));
        when(comunaService.save(any(Comuna.class))).thenReturn(comuna);

        mockMvc.perform(post("/api/v1/direccion/comunas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comuna)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Talca"));
    }

    @Test
    void eliminarComuna() throws Exception {
        doNothing().when(comunaService).delete(1L);

        mockMvc.perform(delete("/api/v1/direccion/comunas/1"))
                .andExpect(status().isOk()); // o .isNoContent() según implementación
    }
}
