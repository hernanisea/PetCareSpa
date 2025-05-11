package com.Microservicios.Gestion.de.Direcciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Long> {
}