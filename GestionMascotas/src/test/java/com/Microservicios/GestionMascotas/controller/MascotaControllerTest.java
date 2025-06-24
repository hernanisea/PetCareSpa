package com.Microservicios.GestionMascotas.controller;

import com.Microservicios.GestionMascotas.dto.MascotaRequestDto;
import com.Microservicios.GestionMascotas.dto.MascotaResponseDto;
import com.Microservicios.GestionMascotas.service.MascotasService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MascotaController.class)
public class MascotaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MascotasService mascotasService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearMascota() throws Exception {
        MascotaRequestDto request = new MascotaRequestDto();
        request.setNombre("Rocky");
        request.setEdad(4);
        request.setSexo("M");
        request.setIdUsuario(1L);
        request.setEspecieId(1L);
        request.setRazaId(2L);

        MascotaResponseDto response = new MascotaResponseDto();
        response.setIdMascota(10L);
        response.setNombre("Rocky");
        response.setEdad(4);
        response.setSexo("M");
        response.setIdUsuario(1L);
        response.setEspecie("Perro");
        response.setRaza("Labrador");

        when(mascotasService.crearMascota(any(MascotaRequestDto.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/mascotas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Rocky"))
                .andExpect(jsonPath("$.especie").value("Perro"));
    }

    @Test
    void testObtenerMascotaPorId() throws Exception {
        MascotaResponseDto response = new MascotaResponseDto();
        response.setIdMascota(1L);
        response.setNombre("Luna");
        response.setEdad(2);
        response.setSexo("F");
        response.setIdUsuario(2L);
        response.setEspecie("Gato");
        response.setRaza("Siames");

        when(mascotasService.obtenerPorId(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/mascotas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Luna"))
                .andExpect(jsonPath("$.especie").value("Gato"));
    }

    @Test
    void testObtenerMascotasPorUsuario() throws Exception {
        MascotaResponseDto mascota1 = new MascotaResponseDto();
        mascota1.setNombre("Max");
        mascota1.setEspecie("Perro");
        mascota1.setRaza("Beagle");

        MascotaResponseDto mascota2 = new MascotaResponseDto();
        mascota2.setNombre("Nina");
        mascota2.setEspecie("Gato");
        mascota2.setRaza("Persa");

        List<MascotaResponseDto> mascotas = Arrays.asList(mascota1, mascota2);

        when(mascotasService.obtenerPorUsuario(null)).thenReturn(mascotas);

        mockMvc.perform(get("/api/v1/mascotas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testEliminarMascota() throws Exception {
        Mockito.doNothing().when(mascotasService).eliminarMascota(5L);

        mockMvc.perform(delete("/api/v1/mascotas/5"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testActualizarMascota() throws Exception {
        MascotaRequestDto request = new MascotaRequestDto();
        request.setNombre("Toby");
        request.setEdad(6);
        request.setSexo("M");
        request.setEspecieId(1L);
        request.setRazaId(1L);

        MascotaResponseDto response = new MascotaResponseDto();
        response.setIdMascota(5L);
        response.setNombre("Toby");
        response.setEdad(6);
        response.setSexo("M");
        response.setEspecie("Perro");
        response.setRaza("Bulldog");

        when(mascotasService.actualizarMascota(Mockito.eq(5L), any(MascotaRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/v1/mascotas/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Toby"))
                .andExpect(jsonPath("$.raza").value("Bulldog"));
    }
}
