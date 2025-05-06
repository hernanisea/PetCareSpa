package com.Microservicios.SoporteTecnico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Microservicios.SoporteTecnico.model.TicketSoporte;

public interface TicketSoporteRepository extends JpaRepository<TicketSoporte, Long> {
    List<TicketSoporte> findByUsuarioId(Long usuarioId);
}
 


