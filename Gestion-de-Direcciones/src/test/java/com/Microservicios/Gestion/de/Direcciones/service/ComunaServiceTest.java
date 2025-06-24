package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.repository.ComunaRepository;

public class ComunaServiceTest {

    @Mock
    private ComunaRepository comunaRepository;

    @InjectMocks
    private ComunaService comunaService;

    private Region region;
    private Comuna comuna;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        region = new Region(1L, "Valparaíso");
        comuna = new Comuna(1L, "Viña del Mar", region);
    }

    @Test
    void testGuardarComuna() {
        when(comunaRepository.save(comuna)).thenReturn(comuna);

        Comuna resultado = comunaService.save(comuna);

        assertThat(resultado.getNombre()).isEqualTo("Viña del Mar");
        verify(comunaRepository, times(1)).save(comuna);
    }

    @Test
    void testBuscarPorId() {
        when(comunaRepository.findById(1L)).thenReturn(Optional.of(comuna));

        Optional<Comuna> resultado = comunaService.findById(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre()).isEqualTo("Viña del Mar");
    }

    @Test
    void testEliminarComuna() {
        comunaService.delete(1L);
        verify(comunaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testListarTodas() {
        when(comunaRepository.findAll()).thenReturn(List.of(comuna));

        List<Comuna> comunas = comunaService.findAll();

        assertThat(comunas).hasSize(1);
        assertThat(comunas.get(0).getNombre()).isEqualTo("Viña del Mar");
    }
}
