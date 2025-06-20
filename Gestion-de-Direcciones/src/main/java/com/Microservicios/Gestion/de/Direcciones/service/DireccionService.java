package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.repository.DireccionRepository;
import com.Microservicios.Gestion.de.Direcciones.webclient.UsuarioClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DireccionService {

    private final DireccionRepository direccionRepository;
    private final UsuarioClient usuarioClient;

    public DireccionService(DireccionRepository direccionRepository, UsuarioClient usuarioClient) {
        this.direccionRepository = direccionRepository;
        this.usuarioClient = usuarioClient;
    }

    public List<Direccion> findAll() {
        return direccionRepository.findAll();
    }

    public Optional<Direccion> findById(Long id) {
        return direccionRepository.findById(id);
    }

    public List<Direccion> findByUsuarioId(Long usuarioId) {
        return direccionRepository.findByUsuarioId(usuarioId);
    }

   public Direccion save(Direccion direccion) {
    try {
        // Verifica con el microservicio si el usuario existe
        usuarioClient.getUsuarioById(direccion.getUsuarioId());
    } catch (RuntimeException ex) {
        // Manejo del error si el usuario no existe
        throw new IllegalArgumentException("No se puede guardar la dirección: el usuario con ID " 
                                           + direccion.getUsuarioId() + " no existe.");
    }

    return direccionRepository.save(direccion);
}

    public void delete(Long id) {
        direccionRepository.deleteById(id);
    }
}
