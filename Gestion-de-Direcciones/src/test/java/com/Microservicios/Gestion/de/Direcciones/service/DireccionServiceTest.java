package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.Microservicios.Gestion.de.Direcciones.dto.DireccionRequest;
import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.repository.ComunaRepository;
import com.Microservicios.Gestion.de.Direcciones.repository.DireccionRepository;
import com.Microservicios.Gestion.de.Direcciones.webclient.UsuarioClient;

public class DireccionServiceTest {

    private DireccionRepository direccionRepo;
    private UsuarioClient usuarioClient;
    private ComunaRepository comunaRepo;
    private DireccionService direccionService;

    @BeforeEach
    void setUp() {
        direccionRepo = mock(DireccionRepository.class);
        usuarioClient = mock(UsuarioClient.class);
        comunaRepo = mock(ComunaRepository.class);
        direccionService = new DireccionService(direccionRepo, usuarioClient, comunaRepo);
    }

    @Test
    void debeGuardarDireccionDesdeRequest() {
        DireccionRequest request = new DireccionRequest();
        request.setCalle("Av. Test");
        request.setDescripcion("Depto A");
        request.setCodigoPostal(12345);
        request.setUsuarioId(1L);
        request.setIdComuna(1L);

        Comuna comuna = new Comuna();
        comuna.setIdComuna(1L);
        comuna.setNombre("Comuna Test");

        when(usuarioClient.getUsuarioById(1L)).thenReturn(Map.of("id", 1));
        when(comunaRepo.findById(1L)).thenReturn(Optional.of(comuna));
        when(direccionRepo.save(Mockito.any(Direccion.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Direccion direccion = direccionService.saveFromDto(request);

        assertThat(direccion.getCalle()).isEqualTo("Av. Test");
        assertThat(direccion.getComuna().getNombre()).isEqualTo("Comuna Test");
    }
}
