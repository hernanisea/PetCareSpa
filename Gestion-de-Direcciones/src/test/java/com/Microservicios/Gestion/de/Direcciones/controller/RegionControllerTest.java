package com.Microservicios.Gestion.de.Direcciones.controller;

import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.service.RegionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegionController.class)
public class RegionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegionService regionService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String TOKEN = "Bearer test-jwt-token";

    @Test
    void listarTodasLasRegiones() throws Exception {
        when(regionService.findAll()).thenReturn(List.of(new Region(1L, "Valparaíso")));

        mockMvc.perform(get("/api/v1/direccion/regiones")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Valparaíso"));
    }

    @Test
    void guardarRegion() throws Exception {
        Region region = new Region(null, "Metropolitana");
        when(regionService.save(any(Region.class))).thenReturn(new Region(1L, "Metropolitana"));

        mockMvc.perform(post("/api/v1/direccion/regiones")
                .header("Authorization", TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(region)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Metropolitana"));
    }

    @Test
    void buscarRegionPorId() throws Exception {
        Region region = new Region(1L, "Ñuble");
        when(regionService.findById(1L)).thenReturn(Optional.of(region));

        mockMvc.perform(get("/api/v1/direccion/regiones/1")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ñuble"));
    }
}
