package com.Microservicios.GestionMascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservicios.GestionMascotas.model.Especie;

@Repository
public interface EspecieRepository extends JpaRepository <Especie, Long> {

}
