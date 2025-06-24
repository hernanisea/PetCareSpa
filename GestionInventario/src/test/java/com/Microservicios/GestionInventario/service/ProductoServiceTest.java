package com.Microservicios.GestionInventario.service;

import com.Microservicios.GestionInventario.model.Producto;
import com.Microservicios.GestionInventario.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    private ProductoRepository productoRepository;
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        productoRepository = mock(ProductoRepository.class);
        productoService = new ProductoService(productoRepository);
    }

    @Test
    void testGuardarProducto() {
        Producto producto = new Producto(null, "Nombre", "Desc", 10, 2, 1500.0);
        when(productoRepository.save(any())).thenReturn(producto);

        Producto guardado = productoService.guardar(producto);
        assertEquals("Nombre", guardado.getNombre());
    }

    @Test
    void testObtenerPorId_Existe() {
        Producto producto = new Producto(1L, "Vacuna", "Desc", 10, 2, 1000.0);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = productoService.obtenerPorId(1L);
        assertEquals(1L, resultado.getIdProducto());
    }

    @Test
    void testObtenerPorId_NoExiste() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> productoService.obtenerPorId(99L));
        assertTrue(ex.getMessage().contains("Producto no encontrado"));
    }
}
