package com.Microservicio.Gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservicio.Gestion.model.TipoServicio;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicio, Long> {

}
