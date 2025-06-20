package com.Microservicios.GestionMascotas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservicios.GestionMascotas.model.Mascotas;

@Repository
public interface MascotaRepository extends JpaRepository<Mascotas, Long> {
    List<Mascotas> findByEspecieIdEspecie(Long idEspecie);
    List<Mascotas> findByRazaIdRaza(Long idRaza);
    List<Mascotas> findByIdUsuario(Long idUsuario);
}

