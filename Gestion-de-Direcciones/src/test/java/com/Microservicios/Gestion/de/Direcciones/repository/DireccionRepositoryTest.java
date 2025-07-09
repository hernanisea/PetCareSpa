package com.Microservicios.Gestion.de.Direcciones.repository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.model.Region;

@DataJpaTest
public class DireccionRepositoryTest {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    private Comuna comuna;

    @BeforeEach
    void setUp() {
        Region region = regionRepository.save(new Region(null, "Test Región"));
        comuna = comunaRepository.save(new Comuna(null, "Test Comuna", region));
    }

    @Test
    void debeGuardarYBuscarDireccionPorUsuarioId() {
        Direccion direccion = new Direccion(null, "Calle 123", "Depto 1", 12345, 1L, comuna);
        direccionRepository.save(direccion);

        List<Direccion> direcciones = direccionRepository.findByUsuarioId(1L);

        assertThat(direcciones).isNotEmpty();
        assertThat(direcciones.get(0).getCalle()).isEqualTo("Calle 123");
    }
}
