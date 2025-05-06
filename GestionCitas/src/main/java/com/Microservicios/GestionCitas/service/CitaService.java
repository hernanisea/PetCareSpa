package com.Microservicios.GestionCitas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Microservicios.GestionCitas.model.Cita;
import com.Microservicios.GestionCitas.repository.CitaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public Cita obtenerCita(long idCita) {
        return citaRepository.findById(idCita).orElse(null);
    }

    public Cita reservarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public void eliminarCita(long idCita) {
        citaRepository.deleteById(idCita);
    }

    public List<Cita> obtenerPorVeterinarioId(Long veterinarioId){
        return citaRepository.findByVeterinarioId(veterinarioId);
    }

    public Cita actualizarCita(long idCita, Cita citaActualizada) {
        Cita citaExistente = citaRepository.findById(idCita).orElse(null);
        if (citaExistente != null) {
            citaExistente.setFechaHora(citaActualizada.getFechaHora());
            citaExistente.setClienteId(citaActualizada.getClienteId());
            citaExistente.setVeterinarioId(citaActualizada.getVeterinarioId());
            citaExistente.setMotivo(citaActualizada.getMotivo());
            citaExistente.setEstado(citaActualizada.getEstado());
            return citaRepository.save(citaExistente);
        }
        return null;
    }
}