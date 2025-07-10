package com.Microservicios.Gestion.de.Direcciones.repository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Microservicios.Gestion.de.Direcciones.model.Region;

@DataJpaTest
public class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void testGuardarYBuscarRegion() {
        Region region = new Region(null, "Test Región");
        Region guardada = regionRepository.save(region);

        Optional<Region> encontrada = regionRepository.findById(guardada.getIdRegion());

        assertThat(encontrada).isPresent();
        assertThat(encontrada.get().getNombre()).isEqualTo("Test Región");
    }
}
