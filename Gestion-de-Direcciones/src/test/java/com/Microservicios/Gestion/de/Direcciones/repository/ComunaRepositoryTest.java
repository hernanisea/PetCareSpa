package com.Microservicios.Gestion.de.Direcciones.repository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Region;

@DataJpaTest
public class ComunaRepositoryTest {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Test
    @DisplayName("Guardar y encontrar comuna")
    void testGuardarYEncontrarComuna() {
        Region region = new Region(null, "Test Región");
        region = regionRepository.save(region);

        Comuna comuna = new Comuna(null, "Test Comuna", region);
        comuna = comunaRepository.save(comuna);

        Optional<Comuna> encontrada = comunaRepository.findById(comuna.getIdComuna());

        assertThat(encontrada).isPresent();
        assertThat(encontrada.get().getNombre()).isEqualTo("Test Comuna");
        assertThat(encontrada.get().getRegion().getNombre()).isEqualTo("Test Región");
    }
}
