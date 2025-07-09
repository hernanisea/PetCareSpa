package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.repository.DireccionRepository;
import com.Microservicios.Gestion.de.Direcciones.webclient.UsuarioClient;

public class DireccionServiceTest {

    private DireccionRepository direccionRepository;
    private UsuarioClient usuarioClient;
    private DireccionService direccionService;

    @BeforeEach
    void setUp() {
        direccionRepository = mock(DireccionRepository.class);
        usuarioClient = mock(UsuarioClient.class);
        direccionService = new DireccionService(direccionRepository, usuarioClient);
    }

    @Test
    void guardarDireccionConUsuarioValido() {
        Comuna comuna = new Comuna();
        Direccion direccion = new Direccion(null, "Test Calle", "Depto", 123, 1L, comuna);

        when(usuarioClient.getUsuarioById(1L)).thenReturn(java.util.Map.of("id", 1L));
        when(direccionRepository.save(direccion)).thenReturn(direccion);

        Direccion resultado = direccionService.save(direccion);

        assertThat(resultado).isNotNull();
        verify(direccionRepository, times(1)).save(direccion);
    }

    @Test
    void buscarDireccionPorId() {
        Direccion direccion = new Direccion();
        when(direccionRepository.findById(1L)).thenReturn(Optional.of(direccion));

        Optional<Direccion> resultado = direccionService.findById(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getCalle()).isEqualTo("Calle X");
    }
}
