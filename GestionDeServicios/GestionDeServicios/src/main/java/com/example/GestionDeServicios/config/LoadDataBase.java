package com.example.GestionDeServicios.config;

import com.example.GestionDeServicios.dto.ServicioRequest;
import com.example.GestionDeServicios.services.ServicioService;
import com.example.GestionDeServicios.services.TipoServicioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDataBase {

    @Bean
    CommandLineRunner initDatabase(ServicioService servicioService, TipoServicioService tipoServicioService) {
        return args -> {
            if (tipoServicioService.findAll().isEmpty() && servicioService.findAll().isEmpty()) {
                var consulta = tipoServicioService.save("Consulta");
                var tratamiento = tipoServicioService.save("Tratamiento");

                ServicioRequest s1 = new ServicioRequest();
                s1.setNombre("victor");
                s1.setDescripcion("Mi perro esta actuando raro");
                s1.setPrecio(1000);
                s1.setTipoServicioId(consulta.getIdTipo());

                ServicioRequest s2 = new ServicioRequest();
                s2.setNombre("Joselito");
                s2.setDescripcion("Necesita hacer un tratamiento de pierna");
                s2.setPrecio(3500);
                s2.setTipoServicioId(tratamiento.getIdTipo());

                servicioService.save(s1);
                servicioService.save(s2);

                System.out.println("Datos iniciales cargados.");
            } else {
                System.out.println("Datos ya existen. No se cargaron nuevos datos.");
            }
        };
    }
}
