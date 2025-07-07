package com.Microservicios.HistorialClinico.controller;

import com.Microservicios.HistorialClinico.dto.HistorialClinicoRequest;
import com.Microservicios.HistorialClinico.model.HistorialClinico;
import com.Microservicios.HistorialClinico.service.HistorialClinicoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HistorialClinicoController.class)
class HistorialClinicoControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private HistorialClinicoService historialClinicoService;

    @Test
    void crearHistorial_deberiaRetornar200() throws Exception {
        HistorialClinicoRequest request = new HistorialClinicoRequest();
        request.setFecha(new Date());
        request.setDiagnostico("Gripe");
        request.setUsuarioId(1L);
        request.setMascotaId(2L);

        HistorialClinico creado = new HistorialClinico(1L, new Date(), null, null, "Gripe", 1L, 2L);

        when(historialClinicoService.guardarHistorial(any(HistorialClinicoRequest.class))).thenReturn(creado);

        mockMvc.perform(post("/api/v1/historiales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diagnostico").value("Gripe"));
    }
}
