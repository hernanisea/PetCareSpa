package com.Microservicios.Gestion.de.Direcciones.repository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.model.Region;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
        direccionRepository.deleteAll();
        comunaRepository.deleteAll();
        regionRepository.deleteAll();

        Region region = new Region(null, "Región de Prueba");
        region = regionRepository.save(region);

        comuna = new Comuna(null, "Comuna de Prueba", region);
        comuna = comunaRepository.save(comuna);
    }

    @Test
    void guardarYBuscarDireccionPorUsuarioId() {
        Direccion direccion = new Direccion(null, "Calle 1", "Depto 101", 12345, 99L, comuna);
        direccionRepository.save(direccion);

        List<Direccion> direcciones = direccionRepository.findByUsuarioId(99L);

        assertThat(direcciones).hasSize(1);
        assertThat(direcciones.get(0).getCalle()).isEqualTo("Calle 1");
    }
}
