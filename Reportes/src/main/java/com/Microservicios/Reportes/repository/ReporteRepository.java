package com.Microservicios.Reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Microservicios.Reportes.model.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long>{

}
