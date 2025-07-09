package com.Microservicios.Gestion.de.Direcciones.repository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Microservicios.Gestion.de.Direcciones.model.Region;

@DataJpaTest
class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    @DisplayName("Guardar una región")
    void guardarRegion() {
        Region region = new Region(null, "Nueva Región");
        Region guardada = regionRepository.save(region);

        assertThat(guardada.getIdRegion()).isNotNull();
        assertThat(guardada.getNombre()).isEqualTo("Nueva Región");
    }

    @Test
    @DisplayName("Buscar región por ID")
    void buscarRegionPorId() {
        Region region = regionRepository.save(new Region(null, "Valparaíso"));
        Optional<Region> encontrada = regionRepository.findById(region.getIdRegion());

        assertThat(encontrada).isPresent();
        assertThat(encontrada.get().getNombre()).isEqualTo("Valparaíso");
    }

    @Test
    @DisplayName("Listar todas las regiones")
    void listarRegiones() {
        regionRepository.save(new Region(null, "Región 1"));
        regionRepository.save(new Region(null, "Región 2"));

        List<Region> regiones = regionRepository.findAll();
        assertThat(regiones).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("Eliminar región por ID")
    void eliminarRegion() {
        Region region = regionRepository.save(new Region(null, "Eliminar Región"));
        regionRepository.deleteById(region.getIdRegion());

        Optional<Region> encontrada = regionRepository.findById(region.getIdRegion());
        assertThat(encontrada).isEmpty();
    }
}
