package com.Microservicios.HistorialClinico.repository;

import com.Microservicios.HistorialClinico.model.HistorialClinico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialClinicoRepository extends JpaRepository<HistorialClinico, Long> {
    List<HistorialClinico> findByMascotaId(Long mascotaId);
}
