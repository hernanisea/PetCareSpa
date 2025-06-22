package com.example.GestionDeServicios.services;

import com.example.GestionDeServicios.model.TipoServicio;
import com.example.GestionDeServicios.repository.TipoServicioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TipoServicioService {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    public List<TipoServicio> findAll() {
        return tipoServicioRepository.findAll();
    }

    public TipoServicio findById(Long id) {
        return tipoServicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de servicio no encontrado con ID: " + id));
    }

    public TipoServicio save(String nombre) {
        TipoServicio tipoServicio = new TipoServicio();
        tipoServicio.setNombre(nombre);
        return tipoServicioRepository.save(tipoServicio);
    }

    public void deleteById(Long id) {
        if (!tipoServicioRepository.existsById(id)) {
            throw new RuntimeException("Tipo de servicio no encontrado con ID: " + id);
        }
        tipoServicioRepository.deleteById(id);
    }
} 
