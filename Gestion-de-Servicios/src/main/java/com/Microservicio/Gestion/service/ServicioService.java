package com.Microservicio.Gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microservicio.Gestion.model.Servicio;
import com.Microservicio.Gestion.model.TipoServicio;
import com.Microservicio.Gestion.repository.ServicioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public List<Servicio> obtenerTodosServicios() {
        return servicioRepository.findAll();
    }

    public Servicio obtenerPorIdServicio(Long idServicio) {
        return servicioRepository.findById(idServicio)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + idServicio));
    }

    public Servicio crearServicio(String nombre, String descripcion, Integer precio, TipoServicio tipoServicio) {
        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setPrecio(precio);
        servicio.setTipoServicio(tipoServicio);
        servicio.setIdTipo(tipoServicio.getIdTipo()); // Asumiendo que tipoServicio tiene getIdTipo()
        return servicioRepository.save(servicio);
    }

    public Servicio actualizar(Long id, String nombre, String descripcion, Integer precio, TipoServicio tipoServicio) {
        Servicio servicio = obtenerPorIdServicio(id);
        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setPrecio(precio);
        servicio.setTipoServicio(tipoServicio);
        servicio.setIdTipo(tipoServicio.getIdTipo());
        return servicioRepository.save(servicio);
    }

    public void eliminar(Long idServicio) {
        servicioRepository.deleteById(idServicio);
    }
}
