package com.Microservicios.HistorialClinico.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Microservicios.HistorialClinico.model.HistorialClinico;
import com.Microservicios.HistorialClinico.repository.HistorialClinicoRepository;

@Service
public class HistorialClinicoService {

    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;

    public List<HistorialClinico> obtenerHistoriales() {
        return historialClinicoRepository.findAll();
    }

    public HistorialClinico obtenerHistorialPorId(Long id) {
        return historialClinicoRepository.findById(id).orElseThrow();
    }

    public HistorialClinico crearHistorial(Date fechaHistorial, String antecedentes, String comentarios, String diagnostico, long idTratamiento, long idReportes) {
        HistorialClinico nuevo = new HistorialClinico(null, fechaHistorial, antecedentes, comentarios, diagnostico, idTratamiento, idReportes);
        return historialClinicoRepository.save(nuevo);
    }

    public HistorialClinico actualizarHistorial(Long id, Date fechaHistorial, String antecedentes, String comentarios, String diagnostico, Long idTratamiento, Long idReportes) {
        HistorialClinico historial = obtenerHistorialPorId(id);
        historial.setFechaHistorial(fechaHistorial);
        historial.setAntecedentees(antecedentes);
        historial.setComentarios(comentarios);
        historial.setDiagnostico(diagnostico);
        historial.setIdTratamiento(idTratamiento);
        historial.setIdReportes(idReportes);
        return historialClinicoRepository.save(historial);
    }

    public void eliminarHistorial(Long id) {
        historialClinicoRepository.deleteById(id);
    }
}
