package com.Microservicio.Gestion.de.Reservas.service;

import com.Microservicio.Gestion.de.Reservas.dto.ReservaRequest;
import com.Microservicio.Gestion.de.Reservas.model.Reservas;
import com.Microservicio.Gestion.de.Reservas.repository.ReservaRepository;
import com.Microservicio.Gestion.de.Reservas.webclient.MascotaClient;
import com.Microservicio.Gestion.de.Reservas.webclient.UsuarioClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private MascotaClient mascotaClient;

    public Reservas guardarReserva(ReservaRequest request) {
        validarEntidades(request.getUsuarioId(), request.getMascotaId());

        Reservas reserva = new Reservas();
        reserva.setFechaReserva(request.getFechaReserva());
        reserva.setUsuarioId(request.getUsuarioId());
        reserva.setMascotaId(request.getMascotaId());
        reserva.setFechaCreacion(LocalDateTime.now());
        reserva.setEstado(true);

        return reservaRepository.save(reserva);
    }

    public Reservas actualizarReserva(Long id, ReservaRequest request) {
        Reservas actual = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        validarEntidades(request.getUsuarioId(), request.getMascotaId());

        actual.setFechaReserva(request.getFechaReserva());
        actual.setUsuarioId(request.getUsuarioId());
        actual.setMascotaId(request.getMascotaId());

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

    private void validarEntidades(Long usuarioId, Long mascotaId) {
        if (usuarioClient.getUsuarioById(usuarioId).isEmpty())
            throw new RuntimeException("Usuario no encontrado");
        if (mascotaClient.getMascotaById(mascotaId).isEmpty())
            throw new RuntimeException("Mascota no encontrada");
    }
}
