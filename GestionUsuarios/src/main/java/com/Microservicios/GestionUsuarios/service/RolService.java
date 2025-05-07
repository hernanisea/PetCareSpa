package com.Microservicios.GestionUsuarios.service;

import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    public Rol obtenerRol(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }
}
