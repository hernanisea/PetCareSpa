package com.Microservicios.GestionMascotas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Microservicios.GestionMascotas.model.Mascotas;
import com.Microservicios.GestionMascotas.repository.MascotaRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service

public class MascotaService {

    @Autowired
private MascotaRepository mascotaRepository;

public List<Mascotas> listarMascotas() {
    return mascotaRepository.findAll();
}

public Mascotas obtenerMascotas(long idMascota) {
    return mascotaRepository.findById(idMascota).orElse(null);
}

public Mascotas agregarMascotas(Mascotas mascotas) {
    return mascotaRepository.save(mascotas);
}

public void eliminarMascotas(long idMascota) {
    mascotaRepository.deleteById(idMascota);
}

public Mascotas mascotaActualizado(long idMascota, Mascotas mascotaActualizado) {
    Mascotas mascotaExistente = mascotaRepository.findById(idMascota).orElse(null);
    if (mascotaExistente != null) {
        mascotaExistente.setIdUsuario(mascotaActualizado.getIdUsuario());
        mascotaExistente.setNombre(mascotaActualizado.getNombre());
        mascotaExistente.setEspecie(mascotaActualizado.getEspecie());
        mascotaExistente.setRaza(mascotaActualizado.getRaza());
        mascotaExistente.setEdad(mascotaActualizado.getEdad());
        mascotaExistente.setSexo(mascotaActualizado.getSexo());
        mascotaExistente.setPesoKg(mascotaActualizado.getPesoKg());
        mascotaExistente.setFechaRegistro(mascotaActualizado.getFechaRegistro());
        return mascotaRepository.save(mascotaExistente);
    }
    return null;
}
}