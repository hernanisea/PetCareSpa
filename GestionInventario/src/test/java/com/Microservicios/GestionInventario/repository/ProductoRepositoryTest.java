package com.Microservicios.GestionInventario.repository;

import com.Microservicios.GestionInventario.model.Producto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private TratamientoRepository tratamientoRepository;

    @BeforeEach
    void limpiarDatos() {
        tratamientoRepository.deleteAll();
        productoRepository.deleteAll();    
    }

    @Test
    void testGuardarYBuscarProducto() {
        productoRepository.deleteAll();

        Producto producto = new Producto(null, "Vacuna A", "Descripción", 5, 3, 1000.0);
        productoRepository.save(producto);

        List<Producto> productos = productoRepository.findAll();

        assertFalse(productos.isEmpty());
        assertEquals("Vacuna A", productos.get(0).getNombre());
    }

    @Test
    void testFindProductosConStockBajo() {
        productoRepository.save(new Producto(null, "Producto1", "Desc", 2, 5, 500.0));
        productoRepository.save(new Producto(null, "Producto2", "Desc", 10, 5, 500.0));

        List<Producto> resultado = productoRepository.findProductosConStockBajo();
        assertEquals(1, resultado.size());
        assertEquals("Producto1", resultado.get(0).getNombre());
    }
}
