package com.Microservicios.GestionInventario.controller;

import com.Microservicios.GestionInventario.model.Producto;
import com.Microservicios.GestionInventario.service.ProductoService;
import com.Microservicios.GestionInventario.service.TratamientoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InventarioController.class)
class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @MockBean
    private TratamientoService tratamientoService;

    @Test
    void testListarProductos() throws Exception {
        Producto p = new Producto(1L, "Vacuna", "desc", 10, 2, 2000.0);
        when(productoService.listarTodos()).thenReturn(List.of(p));

        mockMvc.perform(get("/api/v1/inventario/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Vacuna"));
    }
}
