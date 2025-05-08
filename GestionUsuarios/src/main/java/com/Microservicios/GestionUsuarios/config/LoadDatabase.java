package com.Microservicios.GestionUsuarios.config;

import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(RolRepository rolRepo, UsuarioRepository usuarioRepo) {
        return args -> {
            if (rolRepo.count() == 0 && usuarioRepo.count() == 0) {
                Rol admin = new Rol();
                admin.setNombre("Administrador");
                rolRepo.save(admin);

                Rol cliente = new Rol();
                cliente.setNombre("Cliente");
                rolRepo.save(cliente);

                usuarioRepo.save(new Usuario(null, "victor", "12345", "123456789", admin));
                usuarioRepo.save(new Usuario(null, "Joselito", "12345", "987654321", cliente));

                System.out.println("Datos iniciales cargados.");
            } else {
                System.out.println("Datos ya existen. No se cargaron nuevos datos.");
            }
        };
    }
}
