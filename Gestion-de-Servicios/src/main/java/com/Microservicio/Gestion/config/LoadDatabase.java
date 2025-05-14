package com.Microservicio.Gestion.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Microservicio.Gestion.model.Servicio;
import com.Microservicio.Gestion.model.TipoServicio;
import com.Microservicio.Gestion.repository.ServicioRepository;
import com.Microservicio.Gestion.repository.TipoServicioRepository;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(TipoServicioRepository tipoRepo, ServicioRepository servicioRepo) {
        return args -> {
            if (tipoRepo.count() == 0 && servicioRepo.count() == 0) {

                // Crear tipos de servicio
                TipoServicio consulta = tipoRepo.save(new TipoServicio(0, "Consulta Veterinaria", null));
                TipoServicio cirugia = tipoRepo.save(new TipoServicio(0, "Cirugía General", null));
                TipoServicio vacunacion = tipoRepo.save(new TipoServicio(0, "Vacunación", null));
                TipoServicio estetica = tipoRepo.save(new TipoServicio(0, "Estética Animal", null));
                TipoServicio urgencia = tipoRepo.save(new TipoServicio(0, "Atención de Urgencia", null));

                // Crear servicios (uno por tipo, cinco en total)
                servicioRepo.save(crearServicio("Consulta General", "Evaluación clínica del animal", 15000, consulta));
                servicioRepo.save(crearServicio("Cirugía Esterilización", "Esterilización bajo anestesia general", 55000, cirugia));
                servicioRepo.save(crearServicio("Vacuna Triple Felina", "Vacuna para gatos contra enfermedades virales", 18000, vacunacion));
                servicioRepo.save(crearServicio("Corte de Pelo", "Corte estético según raza y temporada", 10000, estetica));
                servicioRepo.save(crearServicio("Urgencia Nocturna", "Atención médica 24/7 para emergencias", 30000, urgencia));

                System.out.println(" Tipos de servicio y servicios iniciales cargados.");
            } else {
                System.out.println(" Los datos ya existen. No se cargaron nuevos registros.");
            }
        };
    }

    private Servicio crearServicio(String nombre, String descripcion, int precio, TipoServicio tipo) {
        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setPrecio(precio);
        servicio.setTipoServicio(tipo);
        return servicio;
    }
}
