package com.Microservicios.Gestion.de.Direcciones.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.service.ComunaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ComunaController.class)
public class ComunaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComunaService comunaService;

    private Comuna comuna;
    private Region region;

    @BeforeEach
    void setup() {
        region = new Region(1L, "Valparaíso");
        comuna = new Comuna(1L, "Viña del Mar", region);
    }

    @Test
    void testGetAllComunas() throws Exception {
        when(comunaService.findAll()).thenReturn(List.of(comuna));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/direccion/comunas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Viña del Mar"));
    }

    @Test
    void testGetComunaById() throws Exception {
        when(comunaService.findById(1L)).thenReturn(Optional.of(comuna));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/direccion/comunas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Viña del Mar"));
    }

    @Test
    void testSaveComuna() throws Exception {
        when(comunaService.save(any(Comuna.class))).thenReturn(comuna);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/direccion/comunas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(comuna)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Viña del Mar"));
    }

    @Test
    void testDeleteComuna() throws Exception {
        doNothing().when(comunaService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/direccion/comunas/1"))
                .andExpect(status().isOk());
    }
}
