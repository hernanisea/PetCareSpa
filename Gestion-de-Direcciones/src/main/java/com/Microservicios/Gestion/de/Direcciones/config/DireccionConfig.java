package com.Microservicios.Gestion.de.Direcciones.config;

import com.Microservicios.Gestion.de.Direcciones.model.Comuna;
import com.Microservicios.Gestion.de.Direcciones.model.Direccion;
import com.Microservicios.Gestion.de.Direcciones.model.Region;
import com.Microservicios.Gestion.de.Direcciones.repository.ComunaRepository;
import com.Microservicios.Gestion.de.Direcciones.repository.DireccionRepository;
import com.Microservicios.Gestion.de.Direcciones.repository.RegionRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DireccionConfig {

    @Bean
    CommandLineRunner initDatabase(RegionRepository regionRepo, ComunaRepository comunaRepo, DireccionRepository direccionRepo) {
        return args -> {
            if (regionRepo.count() == 0 && comunaRepo.count() == 0 && direccionRepo.count() == 0) {
                // Cargar regiones
                Region region1 = regionRepo.save(new Region(null, "Región Metropolitana"));
                Region region2 = regionRepo.save(new Region(null, "Valparaíso"));
                Region region3 = regionRepo.save(new Region(null, "Biobío"));
                Region region4 = regionRepo.save(new Region(null, "Araucanía"));
                Region region5 = regionRepo.save(new Region(null, "Coquimbo"));

                // Cargar comunas
                Comuna comuna1 = comunaRepo.save(new Comuna(null, "Santiago", region1));
                Comuna comuna2 = comunaRepo.save(new Comuna(null, "Providencia", region1));
                Comuna comuna3 = comunaRepo.save(new Comuna(null, "Viña del Mar", region2));
                Comuna comuna4 = comunaRepo.save(new Comuna(null, "Concepción", region3));
                Comuna comuna5 = comunaRepo.save(new Comuna(null, "Temuco", region4));

                // Cargar direcciones
                direccionRepo.save(new Direccion(null, "Av. Libertador 101", "Cerca del centro", 8320000, comuna1));
                direccionRepo.save(new Direccion(null, "Calle Condell 202", null, 7500000, comuna2));
                direccionRepo.save(new Direccion(null, "Av. Perú 303", "Frente al mar", 2520000, comuna3));
                direccionRepo.save(new Direccion(null, "Paicaví 404", "Edificio rojo", 4100000, comuna4));
                direccionRepo.save(new Direccion(null, "Prat 505", null, 4780000, comuna5));

                System.out.println("✅ Datos iniciales cargados correctamente.");
            } else {
                System.out.println("ℹ️ Datos ya existen. No se cargaron nuevos datos.");
            }
        };
    }
}
