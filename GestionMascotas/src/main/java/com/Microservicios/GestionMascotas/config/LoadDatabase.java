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
import com.Microservicios.GestionMascotas.webclient.UsuarioClient;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(
            EspecieService especieService,
            RazaService razaService,
            MascotasService mascotasService,
            UsuarioClient usuarioClient // validación de usuario externo
    ) {
        return args -> {

            if (especieService.obtenerTodas().isEmpty() && razaService.obtenerTodas().isEmpty()) {

                // Crear 5 especies
                Especie canina = especieService.guardar(new Especie(0, "Canina", null));
                Especie felina = especieService.guardar(new Especie(0, "Felina", null));
                Especie ave = especieService.guardar(new Especie(0, "Ave", null));
                Especie roedor = especieService.guardar(new Especie(0, "Roedor", null));
                Especie reptil = especieService.guardar(new Especie(0, "Reptil", null));

                // Crear 5 razas
                Raza labrador = razaService.guardar(new Raza(0, "Labrador", null));
                Raza siames = razaService.guardar(new Raza(0, "Siamés", null));
                Raza canario = razaService.guardar(new Raza(0, "Canario", null));
                Raza hamster = razaService.guardar(new Raza(0, "Hamster", null));
                Raza iguana = razaService.guardar(new Raza(0, "Iguana Verde", null));

                // Crear 5 mascotas solo si el usuario existe (valida con WebClient)
                try {
                    usuarioClient.validarUsuarioExiste(101L);
                    mascotasService.crearMascota(101L, "Fido", 3, "Macho", 25, new Date(),
                            canina.getIdEspecie(), labrador.getIdRaza(), null);
                } catch (Exception e) {
                    System.out.println("Usuario 101 no existe. No se creó Fido.");
                }

                try {
                    usuarioClient.validarUsuarioExiste(102L);
                    mascotasService.crearMascota(102L, "Mishi", 2, "Hembra", 4, new Date(),
                            felina.getIdEspecie(), siames.getIdRaza(), null);
                } catch (Exception e) {
                    System.out.println("Usuario 102 no existe. No se creó Mishi.");
                }

                try {
                    usuarioClient.validarUsuarioExiste(103L);
                    mascotasService.crearMascota(103L, "Pío", 1, "Macho", 0, new Date(),
                            ave.getIdEspecie(), canario.getIdRaza(), null);
                } catch (Exception e) {
                    System.out.println("Usuario 103 no existe. No se creó Pío.");
                }

                try {
                    usuarioClient.validarUsuarioExiste(104L);
                    mascotasService.crearMascota(104L, "Bola", 1, "Hembra", 0, new Date(),
                            roedor.getIdEspecie(), hamster.getIdRaza(), null);
                } catch (Exception e) {
                    System.out.println("Usuario 104 no existe. No se creó Bola.");
                }

                try {
                    usuarioClient.validarUsuarioExiste(105L);
                    mascotasService.crearMascota(105L, "Rex", 4, "Macho", 2, new Date(),
                            reptil.getIdEspecie(), iguana.getIdRaza(), null);
                } catch (Exception e) {
                    System.out.println("Usuario 105 no existe. No se creó Rex.");
                }

                System.out.println("Especies, razas y mascotas de ejemplo cargadas correctamente.");
            } else {
                System.out.println("Los datos ya existen. No se cargaron nuevos registros.");
            }
        };
    }
}
