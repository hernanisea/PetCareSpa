package com.Microservicios.Gestion.de.Direcciones.controller;

import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.service.DireccionService;
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

@WebMvcTest(DireccionController.class)
public class DireccionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DireccionService direccionService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String TOKEN = "Bearer test-jwt-token";

    @Test
    void obtenerTodasLasDirecciones() throws Exception {
        when(direccionService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/direccion/direcciones")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk());
    }

    @Test
    void guardarDireccion() throws Exception {
        Direccion direccion = new Direccion();
        direccion.setCalle("Nueva calle");
        direccion.setCodigoPostal(12345);
        direccion.setUsuarioId(1L);

        when(direccionService.save(any(Direccion.class))).thenReturn(direccion);

        mockMvc.perform(post("/api/v1/direccion/direcciones")
                .header("Authorization", TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(direccion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.calle").value("Nueva calle"));
    }

    @Test
    void buscarDireccionPorId() throws Exception {
        Direccion direccion = new Direccion();
        direccion.setCalle("Test");
        when(direccionService.findById(1L)).thenReturn(Optional.of(direccion));

        mockMvc.perform(get("/api/v1/direccion/direcciones/1")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.calle").value("Test"));
    }
}
