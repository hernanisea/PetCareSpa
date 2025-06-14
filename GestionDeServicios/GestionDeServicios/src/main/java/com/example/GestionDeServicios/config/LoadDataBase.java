package com.example.GestionDeServicios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.GestionDeServicios.model.Servicio;
import com.example.GestionDeServicios.model.TipoServicio;
import com.example.GestionDeServicios.repository.ServicioRepository;
import com.example.GestionDeServicios.repository.TipoServicioRepository;

@Configuration
public class LoadDataBase {
    @Bean
    CommandLineRunner initDatabase(ServicioRepository servicioRepo, TipoServicioRepository tipoRepo){
        return args ->{
            //si no hay registros en las tablas inserto los de defecto
            if(tipoRepo.count() == 0 && servicioRepo.count() == 0){
                //cargar los roles
                TipoServicio consulta = new TipoServicio();
                consulta.setNombre("Consulta");
                tipoRepo.save(consulta);

                TipoServicio tratamiento = new TipoServicio();
                tratamiento.setNombre("Tratamiento");
                tipoRepo.save(tratamiento);

                //cargar los usuarios
                servicioRepo.save(new Servicio(null,"victor","Mi perro esta actuando raro",1000,consulta));
                servicioRepo.save(new Servicio(null,"Joselito","Necesita hacer un tratamiento de pierna",3500, tratamiento));
                System.out.println("Datos iniciales Cargados");
            }
            else{
                System.out.println("Datos ya existen. No se cargaron nuevos datos");
            }
        };
    }
}