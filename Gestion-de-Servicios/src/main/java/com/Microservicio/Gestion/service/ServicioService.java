package com.Microservicio.Gestion.service;

import com.Microservicio.Gestion.model.Servicio;
import com.Microservicio.Gestion.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public List<Servicio> obtenerTodos() {
        return servicioRepository.findAll();
    }

    public Servicio obtenerPorId(Long id) {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + id));
    }

    public Servicio crear(LocalDateTime fechaReserva, Boolean estado) {
        Servicio servicio = new Servicio();
        servicio.setFechaReserva(fechaReserva);
        servicio.setEstado(estado);
        servicio.setFechaCreacion(LocalDateTime.now());
        return servicioRepository.save(servicio);
    }

    public Servicio actualizar(Long id, LocalDateTime fechaReserva, Boolean estado) {
        Servicio servicio = obtenerPorId(id);
        servicio.setFechaReserva(fechaReserva);
        servicio.setEstado(estado);
        return servicioRepository.save(servicio);
    }

    public void eliminar(Long id) {
        servicioRepository.deleteById(id);
    }
}
