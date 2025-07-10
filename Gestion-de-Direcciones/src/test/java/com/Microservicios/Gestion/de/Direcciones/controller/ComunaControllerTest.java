package com.Microservicios.Gestion.de.Direcciones.controller;

import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.repository.RegionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ComunaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Region region;

    @BeforeEach
    void setup() {
        region = regionRepository.save(new Region(null, "Región Controller"));
    }

    @Test
    void testCrearComuna() throws Exception {
        Comuna comuna = new Comuna(null, "Peñalolén", region);

        mockMvc.perform(post("/api/v1/direccion/comunas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comuna)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Peñalolén")));
    }

    @Test
    void testListarComunas() throws Exception {
        mockMvc.perform(get("/api/v1/direccion/comunas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testBuscarComunaPorIdNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/direccion/comunas/9999"))
                .andExpect(status().isNotFound());
    }
}
