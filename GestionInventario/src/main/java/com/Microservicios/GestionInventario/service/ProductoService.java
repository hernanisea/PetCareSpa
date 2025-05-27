package com.Microservicios.GestionInventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microservicios.GestionInventario.model.Producto;
import com.Microservicios.GestionInventario.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long idProducto) {
        return productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + idProducto));
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizar(Long idProducto, Producto productoActualizado) {
        Producto existente = obtenerPorId(idProducto);
        existente.setNombre(productoActualizado.getNombre());
        existente.setDescripcion(productoActualizado.getDescripcion());
        existente.setStock(productoActualizado.getStock());
        existente.setPrecio(productoActualizado.getPrecio());
        return productoRepository.save(existente);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
