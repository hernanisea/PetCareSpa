package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.repository.RegionRepository;

class RegionServiceTest {

    private RegionRepository regionRepository;
    private RegionService regionService;

    @BeforeEach
    void setUp() {
        regionRepository = Mockito.mock(RegionRepository.class);
        regionService = new RegionService(regionRepository);
    }

    @Test
    void obtenerTodasLasRegiones() {
        List<Region> regiones = Arrays.asList(
                new Region(1L, "Región 1"),
                new Region(2L, "Región 2")
        );

        when(regionRepository.findAll()).thenReturn(regiones);

        List<Region> resultado = regionService.findAll();
        assertThat(resultado).hasSize(2);
    }

    @Test
    void obtenerRegionPorId() {
        Region region = new Region(1L, "Antofagasta");

        when(regionRepository.findById(1L)).thenReturn(Optional.of(region));

        Optional<Region> resultado = regionService.findById(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre()).isEqualTo("Antofagasta");
    }

    @Test
    void guardarRegion() {
        Region region = new Region(null, "Ñuble");
        Region guardada = new Region(1L, "Ñuble");

        when(regionRepository.save(region)).thenReturn(guardada);

        Region resultado = regionService.save(region);

        assertThat(resultado.getIdRegion()).isEqualTo(1L);
        assertThat(resultado.getNombre()).isEqualTo("Ñuble");
    }

    @Test
    void eliminarRegion() {
        Long id = 1L;
        regionService.delete(id);
        verify(regionRepository, times(1)).deleteById(id);
    }
}
