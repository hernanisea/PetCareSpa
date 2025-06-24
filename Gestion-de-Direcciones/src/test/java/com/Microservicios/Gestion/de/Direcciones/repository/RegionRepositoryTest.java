package com.Microservicios.Gestion.de.Direcciones.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Microservicios.Gestion.de.Direcciones.model.Region;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    void guardarYBuscarRegion() {
        Region region = new Region(null, "Región del Maule");
        region = regionRepository.save(region);

        Region encontrada = regionRepository.findById(region.getIdRegion()).orElse(null);

        assertThat(encontrada).isNotNull();
        assertThat(encontrada.getNombre()).isEqualTo("Región del Maule");
    }
}
