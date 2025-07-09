package com.Microservicios.GestionMascotas.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Microservicios.GestionMascotas.dto.MascotaRequestDto;
import com.Microservicios.GestionMascotas.dto.MascotaResponseDto;
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

    public List<MascotaResponseDto> obtenerPorUsuario(Long usuarioId) {
        List<Mascotas> mascotas = (usuarioId != null)
                ? mascotaRepository.findByIdUsuario(usuarioId)
                : mascotaRepository.findAll();
        return mascotas.stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    public List<MascotaResponseDto> obtenerTodasLasMascotas() {
        List<Mascotas> mascotas = mascotaRepository.findAll();
        return mascotas.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public MascotaResponseDto obtenerPorId(Long idMascota) {
        Mascotas mascota = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + idMascota));
        return toResponseDto(mascota);
    }

    public MascotaResponseDto crearMascota(MascotaRequestDto dto) {
        try {
            usuarioClient.validarUsuarioExiste(dto.getIdUsuario());

            Especie especie = especieRepository.findById(dto.getEspecieId())
                    .orElseThrow(() -> new RuntimeException("Especie no encontrada con ID: " + dto.getEspecieId()));

            Raza raza = razaRepository.findById(dto.getRazaId())
                    .orElseThrow(() -> new RuntimeException("Raza no encontrada con ID: " + dto.getRazaId()));

            Mascotas mascota = new Mascotas();
            mascota.setNombre(dto.getNombre());
            mascota.setEdad(dto.getEdad());
            mascota.setSexo(dto.getSexo());
            mascota.setIdUsuario(dto.getIdUsuario());
            mascota.setPesoKg(0);
            mascota.setFechaRegistro(new Date());
            mascota.setEspecie(especie);
            mascota.setRaza(raza);
            Mascotas guardada = mascotaRepository.save(mascota);

            return toResponseDto(guardada);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la mascota: " + e.getMessage(), e);
        }
    }

    @Transactional
    public MascotaResponseDto crearMascotaDesdeParametros(Long idUsuario, String nombre, int edad, String sexo, int pesoKg,
            Date fechaRegistro, Long idEspecie, Long idRaza, Long idReserva) {
        MascotaRequestDto dto = new MascotaRequestDto();
        dto.setIdUsuario(idUsuario);
        dto.setNombre(nombre);
        dto.setEdad(edad);
        dto.setSexo(sexo);
        dto.setEspecieId(idEspecie);
        dto.setRazaId(idRaza);
        return crearMascota(dto);
    }

    @Transactional
    public MascotaResponseDto actualizarMascota(Long idMascota, MascotaRequestDto dto) {
        Mascotas mascota = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + idMascota));

        Especie especie = especieRepository.findById(dto.getEspecieId())
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con ID: " + dto.getEspecieId()));

        Raza raza = razaRepository.findById(dto.getRazaId())
                .orElseThrow(() -> new RuntimeException("Raza no encontrada con ID: " + dto.getRazaId()));

        mascota.setNombre(dto.getNombre());
        mascota.setEdad(dto.getEdad());
        mascota.setSexo(dto.getSexo());
        mascota.setEspecie(especie);
        mascota.setRaza(raza);

        return toResponseDto(mascotaRepository.save(mascota));
    }

    @Transactional
    public void eliminarMascota(Long idMascota) {
        if (!mascotaRepository.existsById(idMascota)) {
            throw new RuntimeException("No existe una mascota con ID: " + idMascota);
        }
        mascotaRepository.deleteById(idMascota);
    }

    private MascotaResponseDto toResponseDto(Mascotas mascota) {
        MascotaResponseDto dto = new MascotaResponseDto();
        dto.setIdMascota(mascota.getIdMascota());
        dto.setNombre(mascota.getNombre());
        dto.setEdad(mascota.getEdad());
        dto.setSexo(mascota.getSexo());
        dto.setIdUsuario(mascota.getIdUsuario());
        dto.setEspecie(mascota.getEspecie().getNombre());
        dto.setRaza(mascota.getRaza().getNombre());
        return dto;
    }

}
