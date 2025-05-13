package com.Microservicios.GestionMascotas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Microservicios.GestionMascotas.model.Especie;
import com.Microservicios.GestionMascotas.repository.EspecieRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EspecieService {

    private final EspecieRepository especieRepository;

    public EspecieService(EspecieRepository especieRepository) {
        this.especieRepository = especieRepository;
    }

    public List<Especie> obtenerTodas() {
        return especieRepository.findAll();
    }

    public Especie obtenerPorId(Long id) {
        return especieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con ID: " + id));
    }

    public Optional<Especie> buscarPorId(Long id) {
        return especieRepository.findById(id);
    }

    public Especie guardar(Especie especie) {
        return especieRepository.save(especie);
    }

    public void eliminar(Long id) {
        if (!especieRepository.existsById(id)) {
            throw new RuntimeException("No existe una especie con ID: " + id);
        }
        especieRepository.deleteById(id);
    }
}
