package com.Microservicios.GestionMascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservicios.GestionMascotas.model.Raza;

@Repository
public interface RazaRepository extends JpaRepository <Raza, Long>  {

}
