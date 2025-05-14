package com.Microservicio.Gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microservicio.Gestion.model.TipoServicio;
import com.Microservicio.Gestion.repository.TipoServicioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TipoServicioService {

    private final TipoServicioRepository tipoServicioRepository;

    public TipoServicioService(TipoServicioRepository tipoServicioRepository) {
        this.tipoServicioRepository = tipoServicioRepository;
    }

    public List<TipoServicio> obtenerTodosTipos() {
        return tipoServicioRepository.findAll();
    }

    public TipoServicio obtenerPorIdTipo(Long id) {
        return tipoServicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de servicio no encontrado con ID: " + id));
    }

    public TipoServicio crear(String nombre) {
        TipoServicio tipo = new TipoServicio();
        tipo.setNombre(nombre);
        return tipoServicioRepository.save(tipo);
    }

    public TipoServicio actualizar(Long id, String nombre) {
        TipoServicio tipo = obtenerPorIdTipo(id);
        tipo.setNombre(nombre);
        return tipoServicioRepository.save(tipo);
    }

    public void eliminar(Long id) {
        tipoServicioRepository.deleteById(id);
    }
}
