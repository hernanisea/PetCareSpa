package com.Microservicios.GestionMascotas.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Microservicios.GestionMascotas.model.Especie;
import com.Microservicios.GestionMascotas.model.Mascotas;
import com.Microservicios.GestionMascotas.model.Raza;
import com.Microservicios.GestionMascotas.repository.EspecieRepository;
import com.Microservicios.GestionMascotas.repository.MascotaRepository;
import com.Microservicios.GestionMascotas.repository.RazaRepository;
import com.Microservicios.GestionMascotas.webclient.UsuarioClient;

import jakarta.transaction.Transactional;

@Service
public class MascotasService {

    private final MascotaRepository mascotaRepository;
    private final EspecieRepository especieRepository;
    private final RazaRepository razaRepository;
    private final UsuarioClient usuarioClient;

    public MascotasService(MascotaRepository mascotaRepository, EspecieRepository especieRepository,
            RazaRepository razaRepository, UsuarioClient usuarioClient) {
        this.mascotaRepository = mascotaRepository;
        this.especieRepository = especieRepository;
        this.razaRepository = razaRepository;
        this.usuarioClient = usuarioClient;
    }

    public List<Mascotas> obtenerMascotas() {
        return mascotaRepository.findAll();
    }

    public List<Mascotas> obtenerPorUsuario(Long idUsuario) {
        usuarioClient.validarUsuarioExiste(idUsuario);
        return mascotaRepository.findByIdUsuario(idUsuario);
    }

    public List<Mascotas> obtenerPorEspecie(Long idEspecie) {
        return mascotaRepository.findByEspecieIdEspecie(idEspecie);
    }

    public List<Mascotas> obtenerPorRaza(Long idRaza) {
        return mascotaRepository.findByRazaIdRaza(idRaza);
    }

    public Mascotas obtenerMascotaPorId(Long idMascota) {
        return mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + idMascota));
    }

    @Transactional
    public Mascotas crearMascota(Long idUsuario, String nombre, Integer edad, String sexo,
            Integer pesoKg, Date fechaRegistro, Long idEspecie, Long idRaza, Long idReserva) {

        usuarioClient.validarUsuarioExiste(idUsuario);

        Especie especie = especieRepository.findById(idEspecie)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con ID: " + idEspecie));

        Raza raza = razaRepository.findById(idRaza)
                .orElseThrow(() -> new RuntimeException("Raza no encontrada con ID: " + idRaza));

        Mascotas mascota = new Mascotas();
        mascota.setIdUsuario(idUsuario);
        mascota.setNombre(nombre);
        mascota.setEdad(edad);
        mascota.setSexo(sexo);
        mascota.setPesoKg(pesoKg);
        mascota.setFechaRegistro(fechaRegistro);
        mascota.setEspecie(especie);
        mascota.setIdReserva(idReserva);
        mascota.setRaza(raza);

        return mascotaRepository.save(mascota);
    }

    @Transactional
    public Mascotas actualizarMascota(Long idMascota, Long idUsuario, String nombre, Integer edad, String sexo,
            Integer pesoKg, Date fechaRegistro, Long idEspecie, Long idRaza, Long idReserva) {

        usuarioClient.validarUsuarioExiste(idUsuario);

        Mascotas mascota = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + idMascota));

        Especie especie = especieRepository.findById(idEspecie)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con ID: " + idEspecie));

        Raza raza = razaRepository.findById(idRaza)
                .orElseThrow(() -> new RuntimeException("Raza no encontrada con ID: " + idRaza));

        mascota.setIdUsuario(idUsuario);
        mascota.setNombre(nombre);
        mascota.setEdad(edad);
        mascota.setSexo(sexo);
        mascota.setPesoKg(pesoKg);
        mascota.setFechaRegistro(fechaRegistro);
        mascota.setEspecie(especie);
        mascota.setIdReserva(idReserva);
        mascota.setRaza(raza);

        return mascotaRepository.save(mascota);
    }

    @Transactional
    public void eliminarMascota(Long idMascota) {
        if (!mascotaRepository.existsById(idMascota)) {
            throw new RuntimeException("No existe una mascota con ID: " + idMascota);
        }
        mascotaRepository.deleteById(idMascota);
    }
}
