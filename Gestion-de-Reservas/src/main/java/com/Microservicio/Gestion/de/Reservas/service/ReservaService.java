package com.Microservicio.Gestion.de.Reservas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Microservicio.Gestion.de.Reservas.model.Reservas;
import com.Microservicio.Gestion.de.Reservas.repository.ReservaRepository;
import com.Microservicio.Gestion.de.Reservas.webclient.MascotaClient;
import com.Microservicio.Gestion.de.Reservas.webclient.UsuarioClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservaService {

    @Autowired 
    private ReservaRepository reservaRepository;
    @Autowired 
    private UsuarioClient usuarioClient;
    @Autowired 
    private MascotaClient mascotaClient;

    public Reservas guardarReserva(Reservas reserva) {
        validarEntidades(reserva);
        return reservaRepository.save(reserva);
    }

    public Reservas actualizarReserva(Long id, Reservas nueva) {
        Reservas actual = reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        validarEntidades(nueva);

        actual.setFechaReserva(nueva.getFechaReserva());
        actual.setFechaCreacion(nueva.getFechaCreacion());
        actual.setEstado(nueva.getEstado());
        actual.setUsuarioId(nueva.getUsuarioId());
        actual.setMascotaId(nueva.getMascotaId());

        return reservaRepository.save(actual);
    }

    public void eliminarReserva(Long id) {
        Reservas reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reservaRepository.delete(reserva);
    }

    public List<Reservas> listarReservas() {
        return reservaRepository.findAll();
    }

    private void validarEntidades(Reservas reserva) {
        if (usuarioClient.getUsuarioById(reserva.getUsuarioId()).isEmpty())
            throw new RuntimeException("Usuario no encontrado");
        if (mascotaClient.getMascotaById(reserva.getMascotaId()).isEmpty())
            throw new RuntimeException("Mascota no encontrada");
    }
}
