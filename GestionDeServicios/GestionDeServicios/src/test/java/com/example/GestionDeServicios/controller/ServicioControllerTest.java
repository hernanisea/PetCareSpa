package com.example.GestionDeServicios.controller;

import com.example.GestionDeServicios.dto.ServicioRequest;
import com.example.GestionDeServicios.model.Servicio;
import com.example.GestionDeServicios.model.TipoServicio;
import com.example.GestionDeServicios.services.ServicioService;
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

@WebMvcTest(ServicioController.class)
public class ServicioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioService servicioService;

    private Servicio servicio;
    private TipoServicio tipoServicio;

    @BeforeEach
    void setUp() {
        tipoServicio = new TipoServicio(1L, "Consulta", null);
        servicio = new Servicio(1L, "Consulta general", "Revisión médica", 25000, tipoServicio);
    }

    @Test
    void getAllServiciosDebeRetornarLista() throws Exception {
        when(servicioService.findAll()).thenReturn(List.of(servicio));

        mockMvc.perform(get("/api/servicios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Consulta general"));
    }

    @Test
    void createServicioDebeRetornar201() throws Exception {
        ServicioRequest request = new ServicioRequest();
        request.setNombre("Vacuna");
        request.setDescripcion("Vacuna contra la rabia");
        request.setPrecio(18000);
        request.setTipoServicioId(1L);

        when(servicioService.save(any())).thenReturn(servicio);

        mockMvc.perform(post("/api/servicios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Consulta general"));
    }
}
