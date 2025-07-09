package com.Microservicios.Gestion.de.Direcciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Microservicios.Gestion.de.Direcciones.dto.DireccionRequest;
import com.Microservicios.Gestion.de.Direcciones.dto.DireccionRequestDto;
import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.repository.ComunaRepository;
import com.Microservicios.Gestion.de.Direcciones.repository.DireccionRepository;
import com.Microservicios.Gestion.de.Direcciones.webclient.UsuarioClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DireccionService {

    private final DireccionRepository direccionRepository;
    private final UsuarioClient usuarioClient;
    private final ComunaRepository comunaRepository;

    public DireccionService(DireccionRepository direccionRepository,
            UsuarioClient usuarioClient,
            ComunaRepository comunaRepository) {
        this.direccionRepository = direccionRepository;
        this.usuarioClient = usuarioClient;
        this.comunaRepository = comunaRepository;
    }

    public List<Direccion> findAll() {
        return direccionRepository.findAll();
    }

    public Optional<Direccion> findById(Long id) {
        return direccionRepository.findById(id);
    }

    public List<Direccion> findByUsuarioId(Long usuarioId) {
        return direccionRepository.findByUsuarioId(usuarioId);
    }

    public Direccion save(DireccionRequestDto dto) {
        validarUsuarioExistente(dto.getUsuarioId());

        Comuna comuna = comunaRepository.findById(dto.getIdComuna())
                .orElseThrow(() -> new IllegalArgumentException("Comuna no encontrada con ID: " + dto.getIdComuna()));

        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getCalle());
        direccion.setDescripcion(dto.getDescripcion());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setUsuarioId(dto.getUsuarioId());
        direccion.setComuna(comuna);

        return direccionRepository.save(direccion);
    }

    public void delete(Long id) {
        direccionRepository.deleteById(id);
    }

    public void crearDireccionSiUsuarioExiste(String calle, String descripcion, int codigoPostal, Comuna comuna, Long usuarioId) {
        try {
            validarUsuarioExistente(usuarioId);
            Direccion direccion = new Direccion(null, calle, descripcion, codigoPostal, usuarioId, comuna);
            direccionRepository.save(direccion);
        } catch (RuntimeException ex) {
            System.err.println("No se creó la dirección para usuario ID " + usuarioId + ": " + ex.getMessage());
        }
    }

    private void validarUsuarioExistente(Long usuarioId) {
        try {
            usuarioClient.getUsuarioById(usuarioId);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("No se puede guardar la dirección: el usuario con ID "
                    + usuarioId + " no existe.");
        }
    }

    public Direccion saveFromDto(DireccionRequest request) {
        validarUsuarioExistente(request.getUsuarioId());

        Comuna comuna = comunaRepository.findById(request.getIdComuna())
                .orElseThrow(() -> new IllegalArgumentException("Comuna no encontrada"));

        Direccion direccion = new Direccion();
        direccion.setCalle(request.getCalle());
        direccion.setDescripcion(request.getDescripcion());
        direccion.setCodigoPostal(request.getCodigoPostal());
        direccion.setUsuarioId(request.getUsuarioId());
        direccion.setComuna(comuna);

        return direccionRepository.save(direccion);
    }

}
