package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.repository.DireccionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DireccionService {
    @Autowired
    private final DireccionRepository direccionRepository;

    public DireccionService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    public List<Direccion> findAll() {
        return direccionRepository.findAll();
    }

    public Optional<Direccion> findById(Long id) {
        return direccionRepository.findById(id);
    }

    public Direccion save(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    public void delete(Long id) {
        direccionRepository.deleteById(id);
    }
}
