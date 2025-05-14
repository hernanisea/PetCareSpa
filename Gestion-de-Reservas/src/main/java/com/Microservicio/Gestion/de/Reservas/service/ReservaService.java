package com.Microservicio.Gestion.de.Reservas.service;

import com.Microservicio.Gestion.de.Reservas.model.Reservas;
import com.Microservicio.Gestion.de.Reservas.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reservas> obtenerReservas() {
        return reservaRepository.findAll();
    }

    public Reservas obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
    }

    public Reservas crearReserva(LocalDateTime fechaReserva) {
        Reservas reserva = new Reservas();
        reserva.setFechaReserva(fechaReserva);
        reserva.setEstado(true);
        reserva.setFechaCreacion(LocalDateTime.now());
        return reservaRepository.save(reserva);
    }

    public Reservas actualizarReserva(Long id, LocalDateTime fechaReserva, Boolean estado) {
        Reservas reserva = obtenerReservaPorId(id);
        reserva.setFechaReserva(fechaReserva);
        reserva.setEstado(estado);
        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
