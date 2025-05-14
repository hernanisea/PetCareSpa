package com.Microservicio.Gestion.config;

import com.Microservicio.Gestion.model.Servicio;
import com.Microservicio.Gestion.repository.ServicioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ServicioRepository servicioRepo) {
        return args -> {
            if (servicioRepo.count() == 0) {

                // Crear y guardar servicios de prueba
                servicioRepo.save(crearServicio(LocalDateTime.of(2025, 5, 15, 10, 0)));
                servicioRepo.save(crearServicio(LocalDateTime.of(2025, 5, 16, 14, 30)));
                servicioRepo.save(crearServicio(LocalDateTime.of(2025, 5, 17, 9, 15)));

                System.out.println("✅ Servicios iniciales cargados.");
            } else {
                System.out.println("⚠️ Los servicios ya existen. No se cargaron nuevos registros.");
            }
        };
    }

    private Servicio crearServicio(LocalDateTime fechaReserva) {
        Servicio servicio = new Servicio();
        servicio.setFechaReserva(fechaReserva);
        servicio.setEstado(true); // Servicio activo
        servicio.setFechaCreacion(LocalDateTime.now());
        return servicio;
    }
}
