package com.Microservicios.Gestion.de.Direcciones.controller;

import com.Microservicios.Gestion.de.Direcciones.dto.DireccionRequest;
import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.model.Region;
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

import static org.mockito.Mockito.when;
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
    void testGetAllDirecciones() throws Exception {
        Region region = new Region(1L, "Región Metropolitana");
        Comuna comuna = new Comuna(1L, "Santiago", region);
        Direccion direccion = new Direccion(1L, "Calle Falsa 123", "Depto 3B", 8320000, 5L, comuna);

        when(direccionService.findAll()).thenReturn(List.of(direccion));

        mockMvc.perform(get("/api/v1/direcciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].calle").value("Calle Falsa 123"));
    }

    @Test
    void testGetDireccionById() throws Exception {
        Region region = new Region(1L, "Región Metropolitana");
        Comuna comuna = new Comuna(1L, "Santiago", region);
        Direccion direccion = new Direccion(1L, "Calle Falsa 123", "Depto 3B", 8320000, 5L, comuna);

        when(direccionService.findById(1L)).thenReturn(Optional.of(direccion));

        mockMvc.perform(get("/api/v1/direcciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.calle").value("Calle Falsa 123"));
    }

    @Test
    void testCreateDireccion() throws Exception {
        DireccionRequest request = new DireccionRequest();
        request.setCalle("Calle Falsa 123");
        request.setDescripcion("Depto 3B");
        request.setCodigoPostal(8320000);
        request.setUsuarioId(5L);
        request.setIdComuna(1L);

        Region region = new Region(1L, "Región Metropolitana");
        Comuna comuna = new Comuna(1L, "Santiago", region);

        Direccion direccion = new Direccion(1L, request.getCalle(), request.getDescripcion(),
                request.getCodigoPostal(), request.getUsuarioId(), comuna);

        when(direccionService.saveFromDto(Mockito.any(DireccionRequest.class))).thenReturn(direccion);

        mockMvc.perform(post("/api/v1/direcciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.calle").value("Calle Falsa 123"))
                .andExpect(jsonPath("$.region").value("Región Metropolitana"));
    }

    @Test
    void testDeleteDireccion() throws Exception {
        Mockito.doNothing().when(direccionService).delete(1L);

        mockMvc.perform(delete("/api/v1/direcciones/1"))
                .andExpect(status().isNoContent());
    }
}
