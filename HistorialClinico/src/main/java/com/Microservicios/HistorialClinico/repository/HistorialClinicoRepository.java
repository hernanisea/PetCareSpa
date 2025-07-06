package com.Microservicios.HistorialClinico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.Microservicios.HistorialClinico.model.HistorialClinico;

@Repository
public interface HistorialClinicoRepository extends JpaRepository<HistorialClinico, Long> {

 
    List<HistorialClinico> findByUsuarioId(Long usuarioId);
    List<HistorialClinico> findByMascotaId(Long mascotaId);
    List<HistorialClinico> findByUsuarioIdAndMascotaId(Long usuarioId, Long mascotaId);
}
