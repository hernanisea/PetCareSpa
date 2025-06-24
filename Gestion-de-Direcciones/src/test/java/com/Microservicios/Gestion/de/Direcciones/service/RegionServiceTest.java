package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.repository.RegionRepository;

public class RegionServiceTest {

    private RegionRepository regionRepository;
    private RegionService regionService;

    @BeforeEach
    void setUp() {
        regionRepository = mock(RegionRepository.class);
        regionService = new RegionService(regionRepository);
    }

    @Test
    void guardarRegion() {
        Region region = new Region(null, "Región de Tarapacá");
        when(regionRepository.save(region)).thenReturn(region);

        Region guardada = regionService.save(region);

        assertThat(guardada).isNotNull();
        verify(regionRepository).save(region);
    }

    @Test
    void buscarRegionPorId() {
        Region region = new Region(1L, "Región de Coquimbo");
        when(regionRepository.findById(1L)).thenReturn(Optional.of(region));

        Optional<Region> encontrada = regionService.findById(1L);

        assertThat(encontrada).isPresent();
        assertThat(encontrada.get().getNombre()).isEqualTo("Región de Coquimbo");
    }
}
