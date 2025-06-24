package com.Microservicio.Gestion.de.Reservas.controller;

import com.Microservicio.Gestion.de.Reservas.dto.ReservaRequest;
import com.Microservicio.Gestion.de.Reservas.model.Reservas;
import com.Microservicio.Gestion.de.Reservas.service.ReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearReservaDeberiaRetornar201() throws Exception {
        ReservaRequest request = new ReservaRequest(
                LocalDateTime.now().plusDays(2),
                1L,
                1L
        );

        Reservas mockReserva = new Reservas(
                1L,
                request.getFechaReserva(),
                true,
                LocalDateTime.now(),
                request.getUsuarioId(),
                request.getMascotaId()
        );

        Mockito.when(reservaService.guardarReserva(any(ReservaRequest.class))).thenReturn(mockReserva);

        mockMvc.perform(post("/api/v1/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idReserva").value(1));
    }

    @Test
    void listarReservasDeberiaRetornar200() throws Exception {
        Reservas reserva = new Reservas(
                1L,
                LocalDateTime.now().plusDays(2),
                true,
                LocalDateTime.now(),
                1L,
                1L
        );

        Mockito.when(reservaService.listarReservas()).thenReturn(List.of(reserva));

        mockMvc.perform(get("/api/v1/reservas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void eliminarReservaDeberiaRetornar204() throws Exception {
        Mockito.doNothing().when(reservaService).eliminarReserva(1L);

        mockMvc.perform(delete("/api/v1/reservas/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void actualizarReservaDeberiaRetornar200() throws Exception {
        ReservaRequest request = new ReservaRequest(
                LocalDateTime.now().plusDays(2),
                1L,
                1L
        );

        Reservas updated = new Reservas(
                1L,
                request.getFechaReserva(),
                true,
                LocalDateTime.now(),
                request.getUsuarioId(),
                request.getMascotaId()
        );

        Mockito.when(reservaService.actualizarReserva(Mockito.eq(1L), any(ReservaRequest.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/v1/reservas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idReserva").value(1));
    }
}
