package com.Microservicios.GestionMascotas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Microservicios.GestionMascotas.model.Especie;
import com.Microservicios.GestionMascotas.model.Raza;
import com.Microservicios.GestionMascotas.repository.RazaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RazaService {

    private final RazaRepository razaRepository;

    public RazaService(RazaRepository razaRepository) {
        this.razaRepository = razaRepository;
    }

    public List<Raza> obtenerTodas() {
        return razaRepository.findAll();
    }

    public Raza obtenerPorId(Long id) {
        return razaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raza no encontrada con ID: " + id));
    }

    public Raza actualizar(Long id, Raza raza) {
        Raza existente = razaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raza no encontrada"));
        existente.setNombre(raza.getNombre());
        return razaRepository.save(existente);
    }

    public Optional<Raza> buscarPorId(Long id) {
        return razaRepository.findById(id);
    }

    public Raza guardar(Raza raza) {
        return razaRepository.save(raza);
    }

    public void eliminar(Long id) {
        if (!razaRepository.existsById(id)) {
            throw new RuntimeException("No existe una raza con ID: " + id);
        }
        razaRepository.deleteById(id);
    }
}
