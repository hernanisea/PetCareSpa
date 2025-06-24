package com.example.Notificaciones.controller;

import com.example.Notificaciones.dto.NotificacionRequest;
import com.example.Notificaciones.model.Notificacion;
import com.example.Notificaciones.security.JwtUtil;
import com.example.Notificaciones.service.NotificacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NotificacionControllerTest {

    private MockMvc mockMvc;
    private NotificacionService service;
    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() {
        service = mock(NotificacionService.class);
        jwtUtil = mock(JwtUtil.class);
        NotificacionController controller = new NotificacionController();
        controller.notificacionService = service;
        controller.jwtUtil = jwtUtil;
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testCrearNotificacionConToken() throws Exception {
        NotificacionRequest request = new NotificacionRequest(1L, "mensaje");
        Notificacion noti = new Notificacion(1L, 1L, "mensaje", new Date(), false, "correo@test.com");

        when(jwtUtil.extraerCorreoDesdeToken(anyString())).thenReturn("correo@test.com");
        when(service.crearDesdeDTO(any(), eq("correo@test.com"))).thenReturn(noti);

        mockMvc.perform(post("/api/v1/notificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensaje").value("mensaje"));
    }

    @Test
    void testObtenerMisNotificaciones() throws Exception {
        when(jwtUtil.extraerCorreoDesdeToken(anyString())).thenReturn("correo@test.com");
        when(service.obtenerPorCreador("correo@test.com")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/notificaciones/mis-notificaciones")
                .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isNoContent());
    }
}
