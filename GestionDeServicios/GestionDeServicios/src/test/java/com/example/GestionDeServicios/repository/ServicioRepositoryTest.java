package com.example.GestionDeServicios.repository;

import com.example.GestionDeServicios.model.Servicio;
import com.example.GestionDeServicios.model.TipoServicio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServicioRepositoryTest {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    @Test
    void testGuardarYBuscarServicio() {
        TipoServicio tipo = new TipoServicio();
        tipo.setNombre("Vacunación");
        tipo = tipoServicioRepository.save(tipo);

        Servicio servicio = new Servicio();
        servicio.setNombre("Vacuna Rabia");
        servicio.setDescripcion("Aplicación de vacuna antirrábica");
        servicio.setPrecio(15000);
        servicio.setTipoServicio(tipo);

        Servicio guardado = servicioRepository.save(servicio);

        assertThat(servicioRepository.findById(guardado.getIdServicio())).isPresent();
        assertThat(servicioRepository.findAll()).hasSizeGreaterThan(0);
    }
}
