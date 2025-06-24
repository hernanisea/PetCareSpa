package com.Microservicios.GestionMascotas.repository;

import com.Microservicios.GestionMascotas.model.Especie;
import com.Microservicios.GestionMascotas.model.Mascotas;
import com.Microservicios.GestionMascotas.model.Raza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // ← ESTO ES CLAVE
public class MascotaRepositoryTest {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private RazaRepository razaRepository;

    private Especie especie;
    private Raza raza;

    @BeforeEach
    void setUp() {
        especie = especieRepository.findAll().stream()
                .filter(e -> e.getNombre().equals("Perro"))
                .findFirst()
                .orElseGet(() -> especieRepository.save(new Especie(0L, "Perro", List.of())));

        raza = razaRepository.findAll().stream()
                .filter(r -> r.getNombre().equals("Labrador"))
                .findFirst()
                .orElseGet(() -> razaRepository.save(new Raza(0L, "Labrador", List.of())));

        mascotaRepository.saveAll(List.of(
                new Mascotas(null, 1001L, "Firulais", 4, "M", 10, new Date(), null, especie, raza),
                new Mascotas(null, 1002L, "Bobby", 2, "M", 12, new Date(), null, especie, raza),
                new Mascotas(null, 1003L, "Nina", 3, "F", 11, new Date(), null, especie, raza)
        ));
    }

    @Test
    void testGuardarMascota() {
        Mascotas mascota = new Mascotas(null, 2000L, "Rocky", 5, "M", 14, new Date(), null, especie, raza);
        Mascotas guardada = mascotaRepository.save(mascota);
        assertThat(guardada.getIdMascota()).isNotNull();
        assertThat(guardada.getNombre()).isEqualTo("Rocky");
    }

    @Test
    void testBuscarPorNombre() {
        List<Mascotas> resultado = mascotaRepository.findAll();
        assertThat(resultado).hasSizeGreaterThanOrEqualTo(3);
        assertThat(resultado).extracting(Mascotas::getNombre).contains("Firulais", "Bobby", "Nina");
    }
}
