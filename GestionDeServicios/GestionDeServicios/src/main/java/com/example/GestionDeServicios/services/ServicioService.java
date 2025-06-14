package com.example.GestionDeServicios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GestionDeServicios.model.Servicio;
import com.example.GestionDeServicios.repository.ServicioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServicioService {
  @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> findAll(){
        return servicioRepository.findAll();
    }

    public Servicio findById(Long id){
        return servicioRepository.findById(id).orElse(null);
    }

    public Servicio save(Servicio servicio){
        return servicioRepository.save(servicio);
    }

    public void deleteById(Long id){
        servicioRepository.deleteById(id);
    }

}
