package com.Microservicios.HistorialClinico.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.reservas.webclient.Microservicios.HistorialClinico.dto.HistorialClinicoRequest;
import com.example.reservas.webclient.Microservicios.HistorialClinico.model.HistorialClinico;
import com.example.reservas.webclient.Microservicios.HistorialClinico.repository.HistorialClinicoRepository;
import com.example.reservas.webclient.Microservicios.HistorialClinico.service.HistorialClinicoService;
import com.example.reservas.webclient.Microservicios.HistorialClinico.webclient.MascotaClient;
import com.example.reservas.webclient.Microservicios.HistorialClinico.webclient.UsuarioClient;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HistorialClinicoServiceTest {

    @Mock
    private HistorialClinicoRepository historialRepo;

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private MascotaClient mascotaClient;

    @InjectMocks
    private HistorialClinicoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardarHistorial_deberiaCrearCorrectamente() {
        HistorialClinicoRequest request = new HistorialClinicoRequest();
        request.setFecha(new Date());
        request.setDiagnostico("Gripe");
        request.setUsuarioId(1L);
        request.setMascotaId(2L);

        Map<String, Object> usuarioMock = new HashMap<>();
        usuarioMock.put("id", 1L);
        Map<String, Object> mascotaMock = new HashMap<>();
        mascotaMock.put("id", 2L);

        when(usuarioClient.getUsuarioById(1L)).thenReturn(usuarioMock);
        when(mascotaClient.getMascotaById(2L)).thenReturn(mascotaMock);
        when(historialRepo.save(any(HistorialClinico.class))).thenAnswer(i -> i.getArgument(0));

        HistorialClinico resultado = service.guardarHistorial(request);

        assertNotNull(resultado);
        assertEquals("Gripe", resultado.getDiagnostico());
        assertEquals(1L, resultado.getUsuarioId());
        assertEquals(2L, resultado.getMascotaId());
    }

    @Test
    void guardarHistorial_usuarioNoExiste_deberiaLanzarExcepcion() {
        HistorialClinicoRequest request = new HistorialClinicoRequest();
        request.setFecha(new Date());
        request.setDiagnostico("Gripe");
        request.setUsuarioId(1L);
        request.setMascotaId(2L);

        when(usuarioClient.getUsuarioById(1L)).thenReturn(null); // simulamos usuario no encontrado

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.guardarHistorial(request));
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void guardarHistorial_mascotaNoExiste_deberiaLanzarExcepcion() {
        HistorialClinicoRequest request = new HistorialClinicoRequest();
        request.setFecha(new Date());
        request.setDiagnostico("Gripe");
        request.setUsuarioId(1L);
        request.setMascotaId(2L);

        Map<String, Object> usuarioMock = new HashMap<>();
        usuarioMock.put("id", 1L);

        when(usuarioClient.getUsuarioById(1L)).thenReturn(usuarioMock);
        when(mascotaClient.getMascotaById(2L)).thenReturn(null); // simulamos mascota no encontrada

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.guardarHistorial(request));
        assertEquals("Mascota no encontrada", ex.getMessage());
    }
}
