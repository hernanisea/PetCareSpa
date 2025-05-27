package com.Microservicios.GestionMascotas.config;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Microservicios.GestionMascotas.model.Especie;
import com.Microservicios.GestionMascotas.model.Raza;
import com.Microservicios.GestionMascotas.service.EspecieService;
import com.Microservicios.GestionMascotas.service.MascotasService;
import com.Microservicios.GestionMascotas.service.RazaService;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(
            EspecieService especieService,
            RazaService razaService,
            MascotasService mascotasService) {
        return args -> {

            if (especieService.obtenerTodas().isEmpty() && razaService.obtenerTodas().isEmpty()) {

                //Crear 5 especies
                Especie canina = especieService.guardar(new Especie(0, "Canina", null));
                Especie felina = especieService.guardar(new Especie(0, "Felina", null));
                Especie ave = especieService.guardar(new Especie(0, "Ave", null));
                Especie roedor = especieService.guardar(new Especie(0, "Roedor", null));
                Especie reptil = especieService.guardar(new Especie(0, "Reptil", null));

                //Crear 5 razas
                Raza labrador = razaService.guardar(new Raza(0, "Labrador", null));
                Raza siames = razaService.guardar(new Raza(0, "Siamés", null));
                Raza canario = razaService.guardar(new Raza(0, "Canario", null));
                Raza hamster = razaService.guardar(new Raza(0, "Hamster", null));
                Raza iguana = razaService.guardar(new Raza(0, "Iguana Verde", null));

                //Crear 5 mascotas
                mascotasService.crearMascota(101, "Fido", 3, "Macho", 25, new Date(),
                        canina.getIdEspecie(), labrador.getIdRaza(), null);

                mascotasService.crearMascota(102, "Mishi", 2, "Hembra", 4, new Date(),
                        felina.getIdEspecie(), siames.getIdRaza(), null);

                mascotasService.crearMascota(103, "Pío", 1, "Macho", 0, new Date(),
                        ave.getIdEspecie(), canario.getIdRaza(), null);

                mascotasService.crearMascota(104, "Bola", 1, "Hembra", 0, new Date(),
                        roedor.getIdEspecie(), hamster.getIdRaza(), null);

                mascotasService.crearMascota(105, "Rex", 4, "Macho", 2, new Date(),
                        reptil.getIdEspecie(), iguana.getIdRaza(), null);

                System.out.println(" Especies, razas y mascotas de ejemplo cargadas correctamente.");
            } else {
                System.out.println(" Los datos ya existen. No se cargaron nuevos registros.");
            }
        };
    }
}
