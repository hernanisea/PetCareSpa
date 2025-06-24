package com.Microservicios.HistorialClinico.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Microservicios.HistorialClinico.dto.HistorialClinicoRequest;
import com.Microservicios.HistorialClinico.model.HistorialClinico;
import com.Microservicios.HistorialClinico.repository.HistorialClinicoRepository;
import com.Microservicios.HistorialClinico.webclient.MascotaClient;
import com.Microservicios.HistorialClinico.webclient.UsuarioClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HistorialClinicoService {

    private final HistorialClinicoRepository historialClinicoRepository;
    private final UsuarioClient usuarioClient;
    private final MascotaClient mascotaClient;

    public HistorialClinicoService(HistorialClinicoRepository historialClinicoRepository, 
                                    UsuarioClient usuarioClient, 
                                    MascotaClient mascotaClient) {
        this.historialClinicoRepository = historialClinicoRepository;
        this.usuarioClient = usuarioClient;
        this.mascotaClient = mascotaClient;
    }

    public List<HistorialClinico> listarHistoriales() {
        return historialClinicoRepository.findAll();
    }

    public HistorialClinico guardarHistorial(HistorialClinicoRequest request) {
        validarUsuarioYMascota(request.getUsuarioId(), request.getMascotaId());
        HistorialClinico historial = new HistorialClinico();
        historial.setFecha(request.getFecha());
        historial.setDiagnostico(request.getDiagnostico());
        historial.setUsuarioId(request.getUsuarioId());
        historial.setMascotaId(request.getMascotaId());
        return historialClinicoRepository.save(historial);
    }

    public HistorialClinico actualizarHistorial(Long id, HistorialClinicoRequest request) {
        HistorialClinico actual = historialClinicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));

        validarUsuarioYMascota(request.getUsuarioId(), request.getMascotaId());

        actual.setFecha(request.getFecha());
        actual.setDiagnostico(request.getDiagnostico());
        actual.setUsuarioId(request.getUsuarioId());
        actual.setMascotaId(request.getMascotaId());

        return historialClinicoRepository.save(actual);
    }

    public void eliminarHistorial(Long id) {
        historialClinicoRepository.deleteById(id);
    }

private void validarUsuarioYMascota(Long usuarioId, Long mascotaId) {
    Map<String, Object> usuario = usuarioClient.getUsuarioById(usuarioId);
    if (usuario == null || usuario.isEmpty()) {
        throw new RuntimeException("Usuario no encontrado");
    }

    Map<String, Object> mascota = mascotaClient.getMascotaById(mascotaId);
    if (mascota == null || mascota.isEmpty()) {
        throw new RuntimeException("Mascota no encontrada");
    }
}
}

