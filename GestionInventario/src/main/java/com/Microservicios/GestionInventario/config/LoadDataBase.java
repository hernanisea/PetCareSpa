package com.Microservicios.GestionInventario.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Microservicios.GestionInventario.model.Producto;
import com.Microservicios.GestionInventario.model.Tratamiento;
import com.Microservicios.GestionInventario.repository.ProductoRepository;
import com.Microservicios.GestionInventario.repository.TratamientoRepository;

@Configuration
public class LoadDataBase {

    @Bean
    CommandLineRunner initDatabase(ProductoRepository productoRepo, TratamientoRepository tratamientoRepo) {
        return args -> {
            if (productoRepo.count() == 0 && tratamientoRepo.count() == 0) {

                // Crear productos
                Producto antibiotico = productoRepo.save(new Producto(
                        null, "Antibiótico Canino", "Antibiótico de amplio espectro para perros", 100, 20, 15.0));

                Producto desparasitante = productoRepo.save(new Producto(
                        null, "Desparasitante Felino", "Desparasitante oral para gatos", 50, 10, 12.0));

                Producto jeringas = productoRepo.save(new Producto(
                        null, "Jeringas 5ml", "Jeringas desechables de 5ml", 200, 100, 0.5));

                Producto vacunaTriple = productoRepo.save(new Producto(
                        null, "Vacuna Triple Felina", "Vacuna contra herpesvirus, calicivirus y panleucopenia felina", 80, 30, 25.0));

                // Crear tratamientos con productos asociados
                Tratamiento infeccionCanina = new Tratamiento(null, 2, antibiotico.getPrecio() * 2, antibiotico);
                tratamientoRepo.save(infeccionCanina);

                Tratamiento desparasitacionFelina = new Tratamiento(null, 1, desparasitante.getPrecio(), desparasitante);
                tratamientoRepo.save(desparasitacionFelina);

                Tratamiento vacunacionBasicaFelina = new Tratamiento(null, 1, vacunaTriple.getPrecio(), vacunaTriple);
                tratamientoRepo.save(vacunacionBasicaFelina);

                Tratamiento aplicacionInyeccion = new Tratamiento(null, 5, jeringas.getPrecio() * 5, jeringas);
                tratamientoRepo.save(aplicacionInyeccion);

                System.out.println("✔ Productos y tratamientos iniciales cargados.");
            } else {
                System.out.println("ℹ Los datos ya existen. No se cargaron nuevos registros.");
            }
        };
    }
}
