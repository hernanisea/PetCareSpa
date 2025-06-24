package com.example.GestionDeServicios.controller;

import com.example.GestionDeServicios.model.TipoServicio;
import com.example.GestionDeServicios.services.TipoServicioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TipoServicioController.class)
public class TipoServicioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoServicioService tipoServicioService;

    private TipoServicio tipo;

    @BeforeEach
    void setUp() {
        tipo = new TipoServicio(1L, "Consulta", null);
    }

    @Test
    void getAllTipoServiciosDebeRetornarLista() throws Exception {
        when(tipoServicioService.findAll()).thenReturn(List.of(tipo));

        mockMvc.perform(get("/api/v1/tiposervicios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Consulta"));
    }

    @Test
    void getTipoServicioByIdDebeRetornarOk() throws Exception {
        when(tipoServicioService.findById(1L)).thenReturn(tipo);

        mockMvc.perform(get("/api/v1/tiposervicios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Consulta"));
    }

    @Test
    void createTipoServicioDebeRetornarCreado() throws Exception {
        when(tipoServicioService.save("Vacunas")).thenReturn(new TipoServicio(2L, "Vacunas", null));

        mockMvc.perform(post("/api/v1/tiposervicios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Vacunas\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Vacunas"));
    }

    @Test
    void deleteTipoServicioDebeRetornarNoContent() throws Exception {
        doNothing().when(tipoServicioService).deleteById(1L);

        mockMvc.perform(delete("/api/v1/tiposervicios/1"))
                .andExpect(status().isNoContent());
    }
}
