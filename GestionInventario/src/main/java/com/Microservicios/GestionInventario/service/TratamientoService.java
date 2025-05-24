package com.Microservicios.GestionInventario.service;

import com.Microservicios.GestionInventario.model.Producto;
import com.Microservicios.GestionInventario.model.Tratamiento;
import com.Microservicios.GestionInventario.repository.ProductoRepository;
import com.Microservicios.GestionInventario.repository.TratamientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TratamientoService {

    private final TratamientoRepository tratamientoRepository;
    private final ProductoRepository productoRepository;

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
