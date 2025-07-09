package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.repository.ComunaRepository;

public class ComunaServiceTest {

    private ComunaRepository comunaRepository;
    private ComunaService comunaService;

    @BeforeEach
    void setUp() {
        comunaRepository = mock(ComunaRepository.class);
        comunaService = new ComunaService(comunaRepository);
    }

    @Test
    void testFindAll() {
        when(comunaRepository.findAll()).thenReturn(Arrays.asList(
                new Comuna(1L, "Santiago", new Region(1L, "Región Metropolitana")),
                new Comuna(2L, "Viña del Mar", new Region(2L, "Valparaíso"))
        ));

        List<Comuna> comunas = comunaService.findAll();

        assertThat(comunas).hasSize(2);
        verify(comunaRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Comuna comuna = new Comuna(1L, "Santiago", new Region(1L, "RM"));
        when(comunaRepository.findById(1L)).thenReturn(Optional.of(comuna));

        Optional<Comuna> result = comunaService.findById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Santiago");
    }

    @Test
    void testSave() {
        Comuna comuna = new Comuna(null, "Providencia", new Region(1L, "RM"));
        when(comunaRepository.save(any(Comuna.class))).thenReturn(comuna);

        Comuna saved = comunaService.save(comuna);

        assertThat(saved.getNombre()).isEqualTo("Providencia");
        verify(comunaRepository, times(1)).save(comuna);
    }

    @Test
    void testDelete() {
        comunaService.delete(1L);
        verify(comunaRepository, times(1)).deleteById(1L);
    }
}
