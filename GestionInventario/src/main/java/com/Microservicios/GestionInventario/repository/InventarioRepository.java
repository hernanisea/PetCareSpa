package com.Microservicios.GestionInventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservicios.GestionInventario.model.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

}
