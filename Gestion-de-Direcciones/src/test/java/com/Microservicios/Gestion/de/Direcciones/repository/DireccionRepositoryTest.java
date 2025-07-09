package com.Microservicios.Gestion.de.Direcciones.repository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void guardarYBuscarDireccionPorUsuario() {
        // Limpiar datos previos
        direccionRepository.deleteAll();
        comunaRepository.deleteAll();
        regionRepository.deleteAll();

        // Crear región y comuna
        Region region = regionRepository.save(new Region(null, "Ñuble"));
        Comuna comuna = comunaRepository.save(new Comuna(null, "Chillán", region));

        // Crear dirección con el constructor correcto
        Direccion direccion = new Direccion(null, "Calle Test", "123", 12345, 77L, comuna);
        direccionRepository.save(direccion);

        // Buscar direcciones por idUsuario
        List<Direccion> direcciones = direccionRepository.findByUsuarioId(77L); 

        assertThat(direcciones).isNotEmpty();
        assertThat(direcciones.get(0).getCalle()).isEqualTo("Calle Test");
    }

}
