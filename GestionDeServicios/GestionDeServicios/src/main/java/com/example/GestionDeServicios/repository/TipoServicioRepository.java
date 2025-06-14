package com.example.GestionDeServicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GestionDeServicios.model.TipoServicio;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicio, Long> {

}
