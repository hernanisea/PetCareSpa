package com.Microservicios.Gestion.de.Direcciones.controller;

import com.Microservicios.Gestion.de.Direcciones.dto.DireccionRequest;
import com.Microservicios.Gestion.de.Direcciones.dto.DireccionResponse;
import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
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

    @Test
    void debeListarDirecciones() throws Exception {
        Direccion direccion = new Direccion(1L, "Calle Falsa 123", "Depto A", 1111, 1L, new Comuna());
        when(direccionService.findAll()).thenReturn(List.of(direccion));

        mockMvc.perform(get("/api/v1/direcciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].calle").value("Calle Falsa 123"));
    }

    @Test
    void debeObtenerDireccionPorId() throws Exception {
        Direccion direccion = new Direccion(1L, "Calle Test", null, 1111, 1L, new Comuna());
        when(direccionService.findById(1L)).thenReturn(Optional.of(direccion));

        mockMvc.perform(get("/api/v1/direcciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.calle").value("Calle Test"));
    }

    @Test
    void debeCrearDireccion() throws Exception {
        DireccionRequest request = new DireccionRequest();
        request.setCalle("Nueva Calle");
        request.setCodigoPostal(1234);
        request.setDescripcion("Depto 1");
        request.setUsuarioId(1L);
        request.setIdComuna(1L);

        Direccion direccion = new Direccion(1L, request.getCalle(), request.getDescripcion(),
                request.getCodigoPostal(), request.getUsuarioId(), new Comuna());

        when(direccionService.saveFromDto(Mockito.any(DireccionRequest.class))).thenReturn(direccion);

        mockMvc.perform(post("/api/v1/direcciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.calle").value("Nueva Calle"));
    }
}
