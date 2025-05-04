package com.Microservicios.ResenasyCalificaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservicios.ResenasyCalificaciones.model.Resena;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {

}
