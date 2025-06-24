package com.Microservicios.GestionInventario.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Microservicios.GestionInventario.dto.TratamientoRequest;
import com.Microservicios.GestionInventario.model.Producto;
import com.Microservicios.GestionInventario.model.Tratamiento;
import com.Microservicios.GestionInventario.repository.ProductoRepository;
import com.Microservicios.GestionInventario.repository.TratamientoRepository;

@Service
public class TratamientoService {

    private final TratamientoRepository tratamientoRepository;
    private final ProductoRepository productoRepository;

    public TratamientoService(TratamientoRepository tratamientoRepository, ProductoRepository productoRepository) {
        this.tratamientoRepository = tratamientoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public Tratamiento crearTratamiento(TratamientoRequest request) {
        Producto producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + request.getIdProducto()));

        if (producto.getStock() < request.getCantidad()) {
            throw new IllegalArgumentException("Stock insuficiente para aplicar tratamiento");
        }

        // Descontar stock
        producto.setStock(producto.getStock() - request.getCantidad());
        productoRepository.save(producto);

        // Calcular subtotal
        double subtotalCalculado = request.getCantidad() * producto.getPrecio();

        // Crear y guardar tratamiento
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setCantidad(request.getCantidad());
        tratamiento.setSubtotal(subtotalCalculado);
        tratamiento.setProducto(producto);

        return tratamientoRepository.save(tratamiento);
    }

    public List<Tratamiento> listarTodos() {
        return tratamientoRepository.findAll();
    }

    public Tratamiento obtenerPorId(Long id) {
        return tratamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado con ID: " + id));
    }

    public void eliminar(Long id) {
        tratamientoRepository.deleteById(id);
    }

    public Tratamiento crearTratamientoConDto(Tratamiento tratamiento, Long idProducto) {
        throw new UnsupportedOperationException("Unimplemented method 'crearTratamientoConDto'");
    }
}
