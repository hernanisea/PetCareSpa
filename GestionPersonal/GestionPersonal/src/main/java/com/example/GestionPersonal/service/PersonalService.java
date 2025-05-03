package com.example.GestionPersonal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GestionPersonal.model.Personal;
import com.example.GestionPersonal.repository.PersonalRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    public List<Personal> listaPersonal() {
        return personalRepository.findAll();
    }

    public Personal buscarPersonal(long id) {
        return personalRepository.findById(id).orElse(null);
    }

    public Personal agregarPersonal(Personal personal) {
        return personalRepository.save(personal);
    }

    public void eliminarPersonal(long id) {
        personalRepository.deleteById(id);
    }

    
    public Personal actualizarPersonal(long id, Personal actualizado) {
        Optional<Personal> existente = personalRepository.findById(id);
        if (existente.isPresent()) {
            Personal personal = existente.get();
            personal.setNombreCompleto(actualizado.getNombreCompleto());
            personal.setEspecialidad(actualizado.getEspecialidad());
            personal.setTelefono(actualizado.getTelefono());
            personal.setEmail(actualizado.getEmail());
            personal.setHorarioDisponible(actualizado.getHorarioDisponible());
            personal.setEstado(actualizado.getEstado());
            return personalRepository.save(personal);
        } else {
            return null;
        }
    }
}
