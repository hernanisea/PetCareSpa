package com.Microservicios.GestionInventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservicios.GestionInventario.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByStockLessThanStockMinimo();
}
