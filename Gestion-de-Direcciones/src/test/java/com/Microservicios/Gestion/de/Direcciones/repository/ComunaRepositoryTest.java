package com.Microservicios.Gestion.de.Direcciones.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Region;

@DataJpaTest
@ActiveProfiles("test") // Usa application-test.properties si lo tienes
public class ComunaRepositoryTest {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    private Region region;

    @BeforeEach
    void setUp() {
        comunaRepository.deleteAll();
        regionRepository.deleteAll();
        region = regionRepository.save(new Region(null, "Región Test"));
    }

    @Test
    void testGuardarComuna() {
        Comuna comuna = new Comuna(null, "Santiago Centro", region);
        Comuna guardada = comunaRepository.save(comuna);

        assertNotNull(guardada.getIdComuna());
        assertEquals("Santiago Centro", guardada.getNombre());
        assertEquals("Región Test", guardada.getRegion().getNombre());
    }

    @Test
    void testBuscarPorId() {
        Comuna comuna = comunaRepository.save(new Comuna(null, "Providencia", region));
        Optional<Comuna> encontrada = comunaRepository.findById(comuna.getIdComuna());

        assertTrue(encontrada.isPresent());
        assertEquals("Providencia", encontrada.get().getNombre());
    }

    @Test
    void testEliminarComuna() {
        Comuna comuna = comunaRepository.save(new Comuna(null, "La Florida", region));
        Long id = comuna.getIdComuna();

        comunaRepository.deleteById(id);
        assertFalse(comunaRepository.findById(id).isPresent());
    }

    @Test
    void testListarComunas() {
        comunaRepository.save(new Comuna(null, "Ñuñoa", region));
        comunaRepository.save(new Comuna(null, "Macul", region));

        List<Comuna> comunas = comunaRepository.findAll();
        assertTrue(comunas.size() >= 2);
    }
}
