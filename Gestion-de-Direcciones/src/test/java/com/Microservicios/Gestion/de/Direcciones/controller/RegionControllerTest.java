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

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
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
    void obtenerTodasLasRegiones() throws Exception {
        when(regionService.findAll()).thenReturn(Arrays.asList(
                new Region(1L, "Región 1"),
                new Region(2L, "Región 2")
        ));

        mockMvc.perform(get("/api/v1/direccion/regiones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void obtenerRegionPorId() throws Exception {
        Region region = new Region(1L, "Arica");

        when(regionService.findById(1L)).thenReturn(Optional.of(region));

        mockMvc.perform(get("/api/v1/direccion/regiones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Arica"));
    }

    @Test
    void crearRegion() throws Exception {
        Region region = new Region(null, "Tarapacá");
        Region regionGuardada = new Region(1L, "Tarapacá");

        when(regionService.save(Mockito.any(Region.class))).thenReturn(regionGuardada);

        mockMvc.perform(post("/api/v1/direccion/regiones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(region)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idRegion").value(1))
                .andExpect(jsonPath("$.nombre").value("Tarapacá"));
    }

    @Test
    void eliminarRegion() throws Exception {
        mockMvc.perform(delete("/api/v1/direccion/regiones/1"))
                .andExpect(status().isOk()); // Tu método no retorna ResponseEntity, por eso espera 200
    }
}
