package com.Microservicio.Gestion.de.Reservas.config;

import com.Microservicio.Gestion.de.Reservas.service.ReservaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ReservaService reservaService) {
        return args -> {
            if (reservaService.listarReservas().isEmpty()) {
                reservaService.crearReserva(LocalDateTime.of(2025, 5, 15, 10, 0), 1L, 1L);
                reservaService.crearReserva(LocalDateTime.of(2025, 5, 16, 14, 30), 2L, 2L);
                reservaService.crearReserva(LocalDateTime.of(2025, 5, 17, 9, 15), 3L, 3L);

                System.out.println("✅ Reservas iniciales cargadas.");
            } else {
                System.out.println("ℹ️ Las reservas ya existen. No se cargaron nuevos registros.");
            }
        };
    }
}
