package com.Microservicios.GestionInventario.config;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Microservicios.GestionInventario.model.Inventario;
import com.Microservicios.GestionInventario.repository.InventarioRepository;

@Configuration
public class LoadDataBase {

    @Bean
    CommandLineRunner initDataBase(InventarioRepository inventarioRepo) {
        return args -> {
            if (inventarioRepo.count() == 0) {
                // Agregar productos de ejemplo
                inventarioRepo.save(new Inventario(
                        null,
                        "Vacuna Antirrábica",
                        "Vacuna para prevenir la rabia en mascotas",
                        150,
                        "Unidades",
                        10,
                        2500,
                        new Date()
                ));

                inventarioRepo.save(new Inventario(
                        null,
                        "Jeringas 3ml",
                        "Paquete de 100 jeringas desechables",
                        50,
                        "Paquetes",
                        5,
                        8000,
                        new Date()
                ));

                inventarioRepo.save(new Inventario(
                        null,
                        "Antibiótico Canino",
                        "Medicamento oral para infecciones bacterianas",
                        80,
                        "Botellas",
                        15,
                        12500,
                        new Date()
                ));

                System.out.println(" Inventario inicial cargado correctamente.");
            } else {
                System.out.println(" El inventario ya contiene datos. No se cargó información inicial.");
            }
        };
    }
}
