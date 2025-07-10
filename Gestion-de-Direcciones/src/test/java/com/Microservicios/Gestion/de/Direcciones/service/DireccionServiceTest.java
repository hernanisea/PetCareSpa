package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.Microservicios.Gestion.de.Direcciones.dto.DireccionRequest;
import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.repository.ComunaRepository;
import com.Microservicios.Gestion.de.Direcciones.repository.DireccionRepository;
import com.Microservicios.Gestion.de.Direcciones.webclient.UsuarioClient;

@SpringBootTest
public class DireccionServiceTest {

    private DireccionService direccionService;
    private DireccionRepository direccionRepository;
    private ComunaRepository comunaRepository;
    private UsuarioClient usuarioClient;

    private Comuna comuna;

    @BeforeEach
    void setup() {
        direccionRepository = Mockito.mock(DireccionRepository.class);
        comunaRepository = Mockito.mock(ComunaRepository.class);
        usuarioClient = Mockito.mock(UsuarioClient.class);

        direccionService = new DireccionService(direccionRepository, usuarioClient, comunaRepository);

        Region region = new Region(1L, "Región Test");
        comuna = new Comuna(1L, "Comuna Test", region);

        Mockito.when(comunaRepository.findById(1L)).thenReturn(Optional.of(comuna));
        Mockito.when(usuarioClient.getUsuarioById(10L)).thenReturn(null);  // Simula que el usuario existe
    }

    @Test
    void guardarDireccionDesdeDTO() {
        DireccionRequest request = new DireccionRequest();
        request.setCalle("Calle Falsa 123");
        request.setDescripcion("Depto A");
        request.setCodigoPostal(7500000);
        request.setUsuarioId(10L);
        request.setIdComuna(1L);

        // Mock del repositorio para simular guardado
        Mockito.when(direccionRepository.save(Mockito.any(Direccion.class)))
               .thenAnswer(invoc -> invoc.getArgument(0));

        Direccion direccion = direccionService.saveFromDto(request);

        assertThat(direccion.getCalle()).isEqualTo("Calle Falsa 123");
        assertThat(direccion.getDescripcion()).isEqualTo("Depto A");
        assertThat(direccion.getCodigoPostal()).isEqualTo(7500000);
        assertThat(direccion.getUsuarioId()).isEqualTo(10L);
        assertThat(direccion.getComuna().getNombre()).isEqualTo("Comuna Test");
    }
}
