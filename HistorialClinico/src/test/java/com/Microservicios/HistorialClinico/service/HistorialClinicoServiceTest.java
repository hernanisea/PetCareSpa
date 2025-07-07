package com.Microservicios.HistorialClinico.service;

import com.Microservicios.HistorialClinico.dto.HistorialClinicoRequest;
import com.Microservicios.HistorialClinico.model.HistorialClinico;
import com.Microservicios.HistorialClinico.repository.HistorialClinicoRepository;
import com.Microservicios.HistorialClinico.webclient.MascotaClient;
import com.Microservicios.HistorialClinico.webclient.UsuarioClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void guardarHistorial_exitoso() {
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
        when(historialRepo.save(any(HistorialClinico.class))).thenAnswer(invocation -> invocation.getArgument(0));

        HistorialClinico resultado = service.guardarHistorial(request);

        assertNotNull(resultado);
        assertEquals("Gripe", resultado.getDiagnostico());
        assertEquals(1L, resultado.getUsuarioId());
        assertEquals(2L, resultado.getMascotaId());
    }

    @Test
    void guardarHistorial_usuarioInvalido() {
        HistorialClinicoRequest request = new HistorialClinicoRequest();
        request.setFecha(new Date());
        request.setDiagnostico("Gripe");
        request.setUsuarioId(1L);
        request.setMascotaId(2L);

        when(usuarioClient.getUsuarioById(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.guardarHistorial(request));
        assertEquals("Usuario no encontrado", exception.getMessage());
    }

    @Test
    void guardarHistorial_mascotaInvalida() {
        HistorialClinicoRequest request = new HistorialClinicoRequest();
        request.setFecha(new Date());
        request.setDiagnostico("Gripe");
        request.setUsuarioId(1L);
        request.setMascotaId(2L);

        Map<String, Object> usuarioMock = new HashMap<>();
        usuarioMock.put("id", 1L);

        when(usuarioClient.getUsuarioById(1L)).thenReturn(usuarioMock);
        when(mascotaClient.getMascotaById(2L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.guardarHistorial(request));
        assertEquals("Mascota no encontrada", exception.getMessage());
    }
}
