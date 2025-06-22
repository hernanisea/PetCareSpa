package com.example.GestionDeServicios.services;

import com.example.GestionDeServicios.dto.ServicioRequest;
import com.example.GestionDeServicios.model.Servicio;
import com.example.GestionDeServicios.model.TipoServicio;
import com.example.GestionDeServicios.repository.ServicioRepository;
import com.example.GestionDeServicios.repository.TipoServicioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    public Servicio findById(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

public Servicio update(Long id, ServicioRequest request) {
    Servicio servicio = servicioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

    TipoServicio tipoServicio = tipoServicioRepository.findById(request.getTipoServicioId())
        .orElseThrow(() -> new RuntimeException("Tipo de servicio no encontrado"));

    servicio.setNombre(request.getNombre());
    servicio.setDescripcion(request.getDescripcion());
    servicio.setPrecio(request.getPrecio());
    servicio.setTipoServicio(tipoServicio);

    return servicioRepository.save(servicio);
}

public Servicio save(ServicioRequest request) {
    TipoServicio tipoServicio = tipoServicioRepository.findById(request.getTipoServicioId())
            .orElseThrow(() -> new RuntimeException("Tipo de servicio no encontrado"));

    Servicio servicio = new Servicio();
    servicio.setNombre(request.getNombre());
    servicio.setDescripcion(request.getDescripcion());
    servicio.setPrecio(request.getPrecio());
    servicio.setTipoServicio(tipoServicio);

    return servicioRepository.save(servicio);
}


    public void deleteById(Long id) {
        servicioRepository.deleteById(id);
    }
} 
