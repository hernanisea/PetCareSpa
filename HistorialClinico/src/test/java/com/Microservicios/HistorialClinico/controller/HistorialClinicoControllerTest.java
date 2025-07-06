package com.Microservicios.HistorialClinico.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

import com.example.reservas.webclient.Microservicios.HistorialClinico.controller.HistorialClinicoController;
import com.example.reservas.webclient.Microservicios.HistorialClinico.dto.HistorialClinicoRequest;
import com.example.reservas.webclient.Microservicios.HistorialClinico.model.HistorialClinico;
import com.example.reservas.webclient.Microservicios.HistorialClinico.service.HistorialClinicoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(HistorialClinicoController.class)
class HistorialClinicoControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private HistorialClinicoService service;

    @Test
    void crearHistorial_deberiaRetornar200() throws Exception {
        HistorialClinicoRequest request = new HistorialClinicoRequest();
        request.setFecha(new Date());
        request.setDiagnostico("Gripe");
        request.setUsuarioId(1L);
        request.setMascotaId(2L);

        HistorialClinico creado = new HistorialClinico(1L, new Date(), null, null, "Gripe", 1L, 2L);

        Mockito.when(service.guardarHistorial(any(HistorialClinicoRequest.class))).thenReturn(creado);

        mockMvc.perform(post("/api/v1/historiales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diagnostico").value("Gripe"));
    }
}
