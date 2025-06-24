package com.Microservicios.GestionInventario.repository;

import com.Microservicios.GestionInventario.model.Producto;
import com.Microservicios.GestionInventario.model.Tratamiento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TratamientoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Test
    void testGuardarTratamiento() {
        Producto producto = productoRepository.save(new Producto(null, "Vacuna", "desc", 10, 5, 1000.0));

        Tratamiento tratamiento = new Tratamiento(null, 2, 2000.0, producto);
        Tratamiento guardado = tratamientoRepository.save(tratamiento);

        assertNotNull(guardado.getIdTratamiento());
        assertEquals(2, guardado.getCantidad());
    }
}
