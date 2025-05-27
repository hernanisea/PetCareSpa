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

import jakarta.transaction.Transactional;

@Service
public class MascotasService {

    private final MascotaRepository mascotaRepository;
    private final EspecieRepository especieRepository;
    private final RazaRepository razaRepository;

    public MascotasService(MascotaRepository mascotaRepository, EspecieRepository especieRepository, RazaRepository razaRepository) {
        this.mascotaRepository = mascotaRepository;
        this.especieRepository = especieRepository;
        this.razaRepository = razaRepository;
    }

    public List<Mascotas> obtenerMascotas() {
        return mascotaRepository.findAll();
    }

    @Transactional
    public Mascotas crearMascota(Integer idUsuario, String nombre, Integer edad, String sexo,
                                 Integer pesoKg, Date fechaRegistro, Long idEspecie, Long idRaza) {

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
        mascota.setRaza(raza);

        return mascotaRepository.save(mascota);
    }

    public Mascotas obtenerMascotaPorId(Long idMascota) {
        return mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + idMascota));
    }

    @Transactional
    public Mascotas actualizarMascota(Long idMascota, Integer idUsuario, String nombre, Integer edad, String sexo,
                                      Integer pesoKg, Date fechaRegistro, Long idEspecie, Long idRaza) {

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
