package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.repository.ComunaRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ComunaService {
    
    @Autowired
    private final ComunaRepository comunaRepository;

    public ComunaService(ComunaRepository comunaRepository) {
        this.comunaRepository = comunaRepository;
    }

    public List<Comuna> findAll() {
        return comunaRepository.findAll();
    }

    public Optional<Comuna> findById(Long id) {
        return comunaRepository.findById(id);
    }

    public Comuna save(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public void delete(Long id) {
        comunaRepository.deleteById(id);
    }
}
