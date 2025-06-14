package com.example.GestionDeServicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GestionDeServicios.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

}
