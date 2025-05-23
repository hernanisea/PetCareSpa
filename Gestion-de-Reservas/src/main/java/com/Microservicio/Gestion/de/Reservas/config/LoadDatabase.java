package com.Microservicio.Gestion.de.Reservas.config;

import com.Microservicio.Gestion.de.Reservas.model.Reservas;
import com.Microservicio.Gestion.de.Reservas.repository.ReservaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ReservaRepository reservaRepo) {
        return args -> {
            if (reservaRepo.count() == 0) {

                
                reservaRepo.save(crearReserva(LocalDateTime.of(2025, 5, 15, 10, 0)));
                reservaRepo.save(crearReserva(LocalDateTime.of(2025, 5, 16, 14, 30)));
                reservaRepo.save(crearReserva(LocalDateTime.of(2025, 5, 17, 9, 15)));

                System.out.println(" Reservas iniciales cargadas.");
            } else {
                System.out.println(" Las reservas ya existen. No se cargaron nuevos registros.");
            }
        };
    }

    private Reservas crearReserva(LocalDateTime fechaReserva) {
        Reservas reserva = new Reservas();
        reserva.setFechaReserva(fechaReserva);
        reserva.setEstado(true); // Reserva activa
        reserva.setFechaCreacion(LocalDateTime.now());
        return reserva;
    }
} 
