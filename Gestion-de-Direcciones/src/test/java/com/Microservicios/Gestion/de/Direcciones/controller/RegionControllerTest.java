package com.Microservicios.Gestion.de.Direcciones.controller;

import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.service.RegionService;
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

@WebMvcTest(RegionController.class)
public class RegionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegionService regionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarRegiones() throws Exception {
        List<Region> regiones = List.of(new Region(1L, "Región 1"), new Region(2L, "Región 2"));
        when(regionService.findAll()).thenReturn(regiones);

        mockMvc.perform(get("/api/v1/direccion/regiones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(regiones.size()));
    }

    @Test
    void obtenerRegionPorId() throws Exception {
        Region region = new Region(1L, "Metropolitana");
        when(regionService.findById(1L)).thenReturn(Optional.of(region));

        mockMvc.perform(get("/api/v1/direccion/regiones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Metropolitana"));
    }

    @Test
    void crearRegion() throws Exception {
        Region region = new Region(null, "Nueva Región");
        Region creada = new Region(1L, "Nueva Región");

        when(regionService.save(any(Region.class))).thenReturn(creada);

        mockMvc.perform(post("/api/v1/direccion/regiones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(region)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nueva Región"));
    }

    @Test
    void eliminarRegion() throws Exception {
        doNothing().when(regionService).delete(1L);

        mockMvc.perform(delete("/api/v1/direccion/regiones/1"))
                .andExpect(status().isNoContent());
    }
}
