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

        return crearReserva(request.getFechaReserva(), request.getUsuarioId(), request.getMascotaId());
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

    public Reservas crearReserva(LocalDateTime fechaReserva, Long usuarioId, Long mascotaId) {
        Reservas reserva = new Reservas();
        reserva.setFechaReserva(fechaReserva);
        reserva.setEstado(true);
        reserva.setFechaCreacion(LocalDateTime.now());
        reserva.setUsuarioId(usuarioId);
        reserva.setMascotaId(mascotaId);
        return reservaRepository.save(reserva);
    }

    private void validarEntidades(Long usuarioId, Long mascotaId) {
        try {
            usuarioClient.getUsuarioById(usuarioId);
        } catch (Exception e) {
            throw new RuntimeException("Usuario no encontrado");
        }

        try {
            mascotaClient.getMascotaById(mascotaId);
        } catch (Exception e) {
            throw new RuntimeException("Mascota no encontrada");
        }
    }
}
