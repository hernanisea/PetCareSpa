package com.example.GestionDeServicios.repository;

import com.example.GestionDeServicios.model.TipoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TipoServicioRepositoryTest {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    @Test
    void testGuardarYBuscarTipoServicio() {
        // Crear y guardar tipo de servicio
        TipoServicio tipo = new TipoServicio();
        tipo.setNombre("Baño");
        TipoServicio guardado = tipoServicioRepository.save(tipo);

        // Validar que se haya guardado correctamente
        assertThat(guardado.getIdTipo()).isNotNull();
        assertThat(guardado.getNombre()).isEqualTo("Baño");

        // Buscar por ID
        Optional<TipoServicio> encontrado = tipoServicioRepository.findById(guardado.getIdTipo());
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo("Baño");
    }

    @Test
    void testEliminarTipoServicio() {
        TipoServicio tipo = new TipoServicio();
        tipo.setNombre("Peluquería");
        TipoServicio guardado = tipoServicioRepository.save(tipo);

        tipoServicioRepository.deleteById(guardado.getIdTipo());

        Optional<TipoServicio> resultado = tipoServicioRepository.findById(guardado.getIdTipo());
        assertThat(resultado).isEmpty();
    }
}
