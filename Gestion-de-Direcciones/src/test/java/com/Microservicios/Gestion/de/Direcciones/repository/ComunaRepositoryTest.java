package com.Microservicios.Gestion.de.Direcciones.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Region;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComunaRepositoryTest {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Test
    void guardarYBuscarComuna() {
        // Eliminar primero direcciones que dependan de comunas
        direccionRepository.deleteAll();
        comunaRepository.deleteAll();
        regionRepository.deleteAll();

        // Crear región
        Region region = new Region(null, "Valparaíso");
        region = regionRepository.save(region);

        // Crear comuna
        Comuna comuna = new Comuna(null, "Viña del Mar", region);
        comunaRepository.save(comuna);

        // Validar
        List<Comuna> comunas = comunaRepository.findAll();
        assertThat(comunas).isNotEmpty();
        assertThat(comunas.get(0).getNombre()).isEqualTo("Viña del Mar");
    }
}
