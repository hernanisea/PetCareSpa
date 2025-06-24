package com.Microservicios.GestionInventario.service;

import com.Microservicios.GestionInventario.dto.TratamientoRequest;
import com.Microservicios.GestionInventario.model.Producto;
import com.Microservicios.GestionInventario.model.Tratamiento;
import com.Microservicios.GestionInventario.repository.ProductoRepository;
import com.Microservicios.GestionInventario.repository.TratamientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TratamientoServiceTest {

    private TratamientoRepository tratamientoRepository;
    private ProductoRepository productoRepository;
    private TratamientoService tratamientoService;

    @BeforeEach
    void setUp() {
        tratamientoRepository = mock(TratamientoRepository.class);
        productoRepository = mock(ProductoRepository.class);
        tratamientoService = new TratamientoService(tratamientoRepository, productoRepository);
    }

    @Test
    void testCrearTratamientoConStockSuficiente() {
        Producto producto = new Producto(1L, "Vacuna", "Desc", 5, 1, 1000.0);
        TratamientoRequest request = new TratamientoRequest(2, null, 1L);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any())).thenReturn(producto);
        when(tratamientoRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Tratamiento resultado = tratamientoService.crearTratamiento(request);

        assertEquals(2, resultado.getCantidad());
        assertEquals(2000.0, resultado.getSubtotal());
        verify(productoRepository).save(producto);
    }

    @Test
    void testCrearTratamientoConStockInsuficiente() {
        Producto producto = new Producto(1L, "Vacuna", "Desc", 1, 1, 1000.0);
        TratamientoRequest request = new TratamientoRequest(5, null, 1L);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        assertThrows(IllegalArgumentException.class, () -> tratamientoService.crearTratamiento(request));
    }
}
