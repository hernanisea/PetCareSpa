package com.Microservicio.Gestion.de.Reservas.repository;

import com.Microservicio.Gestion.de.Reservas.model.Reservas;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservaRepositoryTest {

    @Autowired
    private ReservaRepository reservaRepository;

    @Test
    void guardarYListarReserva() {
        reservaRepository.deleteAll(); 
        Reservas reserva = new Reservas();
        reserva.setFechaReserva(LocalDateTime.now().plusDays(1));
        reserva.setFechaCreacion(LocalDateTime.now());
        reserva.setEstado(true);
        reserva.setUsuarioId(1L);
        reserva.setMascotaId(1L);

        reservaRepository.save(reserva);

        List<Reservas> reservas = reservaRepository.findAll();
        assertThat(reservas).hasSize(1);
    }

}

