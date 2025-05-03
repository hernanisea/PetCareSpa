package com.Microservicios.GestionMascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservicios.GestionMascotas.model.Mascotas;

@Repository
public interface MascotaRepository extends JpaRepository <Mascotas, Long> {

}