package com.Microservicios.SoporteTecnico.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Microservicios.SoporteTecnico.model.TicketSoporte;
import com.Microservicios.SoporteTecnico.model.TicketSoporte.EstadoTicket;
import com.Microservicios.SoporteTecnico.repository.TicketSoporteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TicketSoporteService {

    @Autowired
    private TicketSoporteRepository repository;

    public TicketSoporte crearTicket(TicketSoporte ticket) {
        ticket.setFechaCreacion(LocalDateTime.now());
        ticket.setEstado(EstadoTicket.ABIERTO);
        return repository.save(ticket);
    }

    public TicketSoporte obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<TicketSoporte> obtenerPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public TicketSoporte actualizarEstado(Long id, String estadoStr) {
        TicketSoporte ticket = obtenerPorId(id);
        ticket.setEstado(EstadoTicket.valueOf(estadoStr));
        return repository.save(ticket);
    }

    public TicketSoporte agregarRespuesta(Long id, String respuesta) {
        TicketSoporte ticket = obtenerPorId(id);
        ticket.setRespuesta(respuesta);
        return repository.save(ticket);
    }
}
