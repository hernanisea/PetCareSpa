package com.example.GestionDeServicios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GestionDeServicios.model.TipoServicio;
import com.example.GestionDeServicios.repository.TipoServicioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TipoServicioService {
  @Autowired
    private TipoServicioRepository tipoServicioRepository;

    public List<TipoServicio> findAll(){
        return tipoServicioRepository.findAll();
    }

    public TipoServicio findById(Long id){
        return tipoServicioRepository.findById(id).orElse(null);
    }

    public TipoServicio save(TipoServicio servicio){
        return tipoServicioRepository.save(servicio);
    }

    public void deleteById(Long id){
        tipoServicioRepository.deleteById(id);
    }

}
