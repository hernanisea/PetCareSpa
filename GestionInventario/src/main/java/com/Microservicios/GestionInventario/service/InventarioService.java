package com.Microservicios.GestionInventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Microservicios.GestionInventario.model.Inventario;
import com.Microservicios.GestionInventario.repository.InventarioRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service

public class InventarioService {

    @Autowired
private InventarioRepository inventarioRepository;

public List<Inventario> listarInventario() {
    return inventarioRepository.findAll();
}

public Inventario obtenerProducto(long idProducto) {
    return inventarioRepository.findById(idProducto).orElse(null);
}

public Inventario agregarInventario(Inventario inventario) {
    return inventarioRepository.save(inventario);
}

public void eliminarProducto(long idProducto) {
    inventarioRepository.deleteById(idProducto);
}

public Inventario inventarioActualizado(long idProducto, Inventario inventarioActualizado) {
    Inventario productoExistente = inventarioRepository.findById(idProducto).orElse(null);
    if (productoExistente != null) {
        productoExistente.setNombreProducto(inventarioActualizado.getNombreProducto());
        productoExistente.setDescripcion(inventarioActualizado.getDescripcion());
        productoExistente.setCantidad(inventarioActualizado.getCantidad());
        productoExistente.setUnidadMedida(inventarioActualizado.getUnidadMedida());
        productoExistente.setStockMinimo(inventarioActualizado.getStockMinimo());
        productoExistente.setFechaIngreso(inventarioActualizado.getFechaIngreso());
        return inventarioRepository.save(productoExistente);
    }
    return null;
}
}
