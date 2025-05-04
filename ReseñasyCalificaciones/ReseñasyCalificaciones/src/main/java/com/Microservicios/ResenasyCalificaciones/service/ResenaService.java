package com.Microservicios.ResenasyCalificaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Microservicios.ResenasyCalificaciones.model.Resena;
import com.Microservicios.ResenasyCalificaciones.repository.ResenaRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service

public class ResenaService {

@Autowired
private ResenaRepository resenaRepository;

public List<Resena> listaResena() {
    return resenaRepository.findAll();
}

public Resena obtenerResena(long id) {
    return resenaRepository.findById(id).orElse(null);
}

public Resena agregarResena(Resena resena) {
    return resenaRepository.save(resena);
}

public void eliminarResena(long id) {
    resenaRepository.deleteById(id);
}

}