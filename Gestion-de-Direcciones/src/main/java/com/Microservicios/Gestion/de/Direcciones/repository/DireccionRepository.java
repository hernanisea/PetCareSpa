package com.Microservicios.Gestion.de.Direcciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservicios.Gestion.de.Direcciones.model.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}
