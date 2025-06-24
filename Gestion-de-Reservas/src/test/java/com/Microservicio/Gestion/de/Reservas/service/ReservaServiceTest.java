package com.Microservicio.Gestion.de.Reservas.service;

import com.Microservicio.Gestion.de.Reservas.dto.ReservaRequest;
import com.Microservicio.Gestion.de.Reservas.model.Reservas;
import com.Microservicio.Gestion.de.Reservas.repository.ReservaRepository;
import com.Microservicio.Gestion.de.Reservas.webclient.MascotaClient;
import com.Microservicio.Gestion.de.Reservas.webclient.UsuarioClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private MascotaClient mascotaClient;

    @InjectMocks
    private ReservaService reservaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardarReservaDebeRetornarReservaGuardada() {
        ReservaRequest request = new ReservaRequest(LocalDateTime.now().plusDays(1), 1L, 1L);

        when(usuarioClient.getUsuarioById(1L)).thenReturn(Map.of("id", 1));
        when(mascotaClient.getMascotaById(1L)).thenReturn(Map.of("id", 1));
        when(reservaRepository.save(any(Reservas.class))).thenAnswer(i -> i.getArgument(0));

        Reservas resultado = reservaService.guardarReserva(request);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getUsuarioId()).isEqualTo(1L);
        assertThat(resultado.getMascotaId()).isEqualTo(1L);
        assertThat(resultado.getEstado()).isTrue();
    }

}
