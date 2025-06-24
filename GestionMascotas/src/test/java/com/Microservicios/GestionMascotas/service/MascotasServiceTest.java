package com.Microservicios.GestionMascotas.service;

import com.Microservicios.GestionMascotas.dto.MascotaRequestDto;
import com.Microservicios.GestionMascotas.dto.MascotaResponseDto;
import com.Microservicios.GestionMascotas.model.Especie;
import com.Microservicios.GestionMascotas.model.Mascotas;
import com.Microservicios.GestionMascotas.model.Raza;
import com.Microservicios.GestionMascotas.repository.EspecieRepository;
import com.Microservicios.GestionMascotas.repository.MascotaRepository;
import com.Microservicios.GestionMascotas.repository.RazaRepository;
import com.Microservicios.GestionMascotas.webclient.UsuarioClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MascotasServiceTest {

    private MascotaRepository mascotaRepository;
    private EspecieRepository especieRepository;
    private RazaRepository razaRepository;
    private UsuarioClient usuarioClient;
    private MascotasService mascotasService;

    @BeforeEach
    void setUp() {
        mascotaRepository = mock(MascotaRepository.class);
        especieRepository = mock(EspecieRepository.class);
        razaRepository = mock(RazaRepository.class);
        usuarioClient = mock(UsuarioClient.class);
        mascotasService = new MascotasService(mascotaRepository, especieRepository, razaRepository, usuarioClient);
    }

    @Test
    void testCrearMascota_deberiaGuardarCorrectamente() {
        // Arrange
        MascotaRequestDto request = new MascotaRequestDto();
        request.setNombre("Firulais");
        request.setEdad(3);
        request.setSexo("M");
        request.setIdUsuario(1L);
        request.setEspecieId(10L);
        request.setRazaId(20L);

        Especie especie = new Especie();
        especie.setIdEspecie(10L);
        especie.setNombre("Perro");

        Raza raza = new Raza();
        raza.setIdRaza(20L);
        raza.setNombre("Labrador");

        Mascotas mascota = new Mascotas();
        mascota.setIdMascota(100L);
        mascota.setNombre("Firulais");
        mascota.setEdad(3);
        mascota.setSexo("M");
        mascota.setIdUsuario(1L);
        mascota.setFechaRegistro(new Date());
        mascota.setEspecie(especie);
        mascota.setRaza(raza);

        // Stubs
        doNothing().when(usuarioClient).validarUsuarioExiste(1L);
        when(especieRepository.findById(10L)).thenReturn(Optional.of(especie));
        when(razaRepository.findById(20L)).thenReturn(Optional.of(raza));
        when(mascotaRepository.save(any(Mascotas.class))).thenReturn(mascota);

        // Act
        MascotaResponseDto response = mascotasService.crearMascota(request);

        // Assert
        assertNotNull(response);
        assertEquals("Firulais", response.getNombre());
        assertEquals("Perro", response.getEspecie());
        assertEquals("Labrador", response.getRaza());
    }

    @Test
    void testObtenerPorId_deberiaRetornarMascota() {
        // Arrange
        Long id = 100L;
        Especie especie = new Especie();
        especie.setNombre("Gato");
        Raza raza = new Raza();
        raza.setNombre("Siames");

        Mascotas mascota = new Mascotas();
        mascota.setIdMascota(id);
        mascota.setNombre("Michi");
        mascota.setEdad(2);
        mascota.setSexo("F");
        mascota.setIdUsuario(2L);
        mascota.setEspecie(especie);
        mascota.setRaza(raza);

        when(mascotaRepository.findById(id)).thenReturn(Optional.of(mascota));

        // Act
        MascotaResponseDto response = mascotasService.obtenerPorId(id);

        // Assert
        assertNotNull(response);
        assertEquals("Michi", response.getNombre());
        assertEquals("Gato", response.getEspecie());
        assertEquals("Siames", response.getRaza());
    }

    @Test
    void testObtenerPorId_mascotaNoExiste_deberiaLanzarExcepcion() {
        // Arrange
        Long id = 999L;
        when(mascotaRepository.findById(id)).thenReturn(Optional.empty());

        // Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            mascotasService.obtenerPorId(id);
        });

        assertEquals("Mascota no encontrada con ID: " + id, exception.getMessage());
    }
}
