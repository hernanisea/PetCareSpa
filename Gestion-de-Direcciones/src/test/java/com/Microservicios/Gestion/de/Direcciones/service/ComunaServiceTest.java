package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.repository.ComunaRepository;
import com.Microservicios.Gestion.de.Direcciones.repository.DireccionRepository;
import com.Microservicios.Gestion.de.Direcciones.repository.RegionRepository;

@SpringBootTest
public class ComunaServiceTest {

    @Autowired
    private ComunaService comunaService;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    private Region region;

    @BeforeEach
    void setUp() {
        // Eliminar primero direcciones para evitar errores de restricción
        direccionRepository.deleteAll();
        comunaRepository.deleteAll();
        regionRepository.deleteAll();

        region = regionRepository.save(new Region(null, "Región Service"));
    }

    @Test
    void guardarComuna() {
        Comuna comuna = comunaService.save(new Comuna(null, "Comuna Nueva", region));
        assertThat(comuna.getIdComuna()).isNotNull();
    }

    @Test
    void eliminarComuna() {
        Comuna comuna = comunaService.save(new Comuna(null, "Comuna A Eliminar", region));
        comunaService.delete(comuna.getIdComuna());
        assertThat(comunaService.findById(comuna.getIdComuna())).isEmpty();
    }

    @Test
    void obtenerPorId() {
        Comuna comuna = comunaService.save(new Comuna(null, "Comuna A Buscar", region));
        Optional<Comuna> resultado = comunaService.findById(comuna.getIdComuna());
        assertThat(resultado).isPresent();
    }
}
