package com.Microservicios.GestionInventario.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Microservicios.GestionInventario.model.Producto;
import com.Microservicios.GestionInventario.model.Tratamiento;
import com.Microservicios.GestionInventario.repository.ProductoRepository;
import com.Microservicios.GestionInventario.repository.TratamientoRepository;

@Service
public class TratamientoService {

    private final TratamientoRepository tratamientoRepository;
    private final ProductoRepository productoRepository;

    @Transactional
    public Tratamiento crearTratamiento(Tratamiento tratamiento) {
        Producto producto = productoRepository.findById(tratamiento.getProducto().getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() < tratamiento.getCantidad()) {
            throw new IllegalArgumentException("Stock insuficiente para aplicar tratamiento");
        }

        producto.setStock(producto.getStock() - tratamiento.getCantidad());
        productoRepository.save(producto);

        tratamiento.setSubtotal(tratamiento.getCantidad() * producto.getPrecio());
        tratamiento.setProducto(producto);

        return tratamientoRepository.save(tratamiento);
    }

    public TratamientoService(TratamientoRepository tratamientoRepository, ProductoRepository productoRepository) {
        this.tratamientoRepository = tratamientoRepository;
        this.productoRepository = productoRepository;
    }

    public List<Tratamiento> listarTodos() {
        return tratamientoRepository.findAll();
    }

    public Tratamiento obtenerPorId(Long id) {
        return tratamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado con ID: " + id));
    }

    public Tratamiento guardar(Tratamiento tratamiento, Long idProducto) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + idProducto));
        tratamiento.setProducto(producto);
        return tratamientoRepository.save(tratamiento);
    }

    public void eliminar(Long id) {
        tratamientoRepository.deleteById(id);
    }

}
