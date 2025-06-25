package com.Microservicios.Gestion.de.Direcciones.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.repository.ComunaRepository;
import com.Microservicios.Gestion.de.Direcciones.repository.RegionRepository;
import com.Microservicios.Gestion.de.Direcciones.service.DireccionService;

@Configuration
public class DireccionConfig {

    @Bean
    CommandLineRunner initDatabase(RegionRepository regionRepo,
                                    ComunaRepository comunaRepo,
                                    DireccionService direccionService) {
        return args -> {
            if (regionRepo.count() == 0 && comunaRepo.count() == 0 && direccionService.findAll().isEmpty()) {
                // Crear regiones
                Region region1 = regionRepo.save(new Region(null, "Región Metropolitana"));
                Region region2 = regionRepo.save(new Region(null, "Valparaíso"));
                Region region3 = regionRepo.save(new Region(null, "Biobío"));
                Region region4 = regionRepo.save(new Region(null, "Araucanía"));
                Region region5 = regionRepo.save(new Region(null, "Coquimbo"));

                // Crear comunas
                Comuna comuna1 = comunaRepo.save(new Comuna(null, "Santiago", region1));
                Comuna comuna2 = comunaRepo.save(new Comuna(null, "Providencia", region1));
                Comuna comuna3 = comunaRepo.save(new Comuna(null, "Viña del Mar", region2));
                Comuna comuna4 = comunaRepo.save(new Comuna(null, "Concepción", region3));
                Comuna comuna5 = comunaRepo.save(new Comuna(null, "Temuco", region4));

                // Crear direcciones para IDs de usuario conocidos (preconfigurados)
                direccionService.crearDireccionSiUsuarioExiste("Av. Libertador 101", "Cerca del centro", 8320000, comuna1, 1L);
                direccionService.crearDireccionSiUsuarioExiste("Calle Condell 202", null, 7500000, comuna2, 2L);
                direccionService.crearDireccionSiUsuarioExiste("Av. Perú 303", "Frente al mar", 2520000, comuna3, 3L);
                direccionService.crearDireccionSiUsuarioExiste("Paicaví 404", "Edificio rojo", 4100000, comuna4, 4L);
                direccionService.crearDireccionSiUsuarioExiste("Prat 505", null, 4780000, comuna5, 5L);

                System.out.println("Datos iniciales cargados correctamente.");
            } else {
                System.out.println("Los datos ya existen. No se cargaron nuevos registros.");
            }
        };
    }
}