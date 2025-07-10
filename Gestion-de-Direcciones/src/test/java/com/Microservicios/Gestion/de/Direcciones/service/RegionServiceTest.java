package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.repository.RegionRepository;

public class RegionServiceTest {

    private RegionRepository regionRepository;
    private RegionService regionService;

    @BeforeEach
    public void setup() {
        regionRepository = mock(RegionRepository.class);
        regionService = new RegionService(regionRepository);
    }

    @Test
    public void testGuardarRegion() {
        Region region = new Region(null, "Región Test");
        when(regionRepository.save(region)).thenReturn(new Region(1L, "Región Test"));

        Region resultado = regionService.save(region);

        assertThat(resultado.getIdRegion()).isEqualTo(1L);
        assertThat(resultado.getNombre()).isEqualTo("Región Test");
    }

    @Test
    public void testBuscarPorId() {
        Region region = new Region(1L, "Región Test");
        when(regionRepository.findById(1L)).thenReturn(Optional.of(region));

        Optional<Region> resultado = regionService.findById(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre()).isEqualTo("Región Test");
    }

    @Test
    public void testEliminar() {
        regionService.delete(1L);
        verify(regionRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testListarTodas() {
        when(regionRepository.findAll()).thenReturn(List.of(
                new Region(1L, "R1"),
                new Region(2L, "R2")
        ));

        List<Region> regiones = regionService.findAll();

        assertThat(regiones).hasSize(2);
        assertThat(regiones.get(0).getNombre()).isEqualTo("R1");
    }
}
