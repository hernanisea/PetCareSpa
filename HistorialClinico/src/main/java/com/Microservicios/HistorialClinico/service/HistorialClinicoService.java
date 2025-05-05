package com.Microservicios.HistorialClinico.service;

import com.Microservicios.HistorialClinico.model.HistorialClinico;
import com.Microservicios.HistorialClinico.repository.HistorialClinicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class HistorialClinicoService {

    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;

    public List<HistorialClinico>obtenerTodos(){
        return historialClinicoRepository.findAll();
    }


    public Optional<HistorialClinico> obtenerPorId(Long id){
        return historialClinicoRepository.findById(id);


    }


    public List<HistorialClinico> obtenerPorMascotaId(Long mascotaId){
        return historialClinicoRepository.findByMascotaId(mascotaId);
    }

    public HistorialClinico guardar(HistorialClinico historialClinico){
        return historialClinicoRepository.save(historialClinico);
    }

}
