package com.Microservicios.GestionUsuarios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;
import com.Microservicios.GestionUsuarios.service.UsuarioService;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(RolRepository rolRepo, UsuarioRepository usuarioRepo, UsuarioService usuarioService) {
        return args -> {
            if (rolRepo.count() == 0 && usuarioRepo.count() == 0) {
                Rol adminSistema = rolRepo.save(new Rol("Administrador del Sistema"));
                Rol coordinadorClinica = rolRepo.save(new Rol("Coordinador de Clínica"));
                Rol veterinario = rolRepo.save(new Rol("Veterinario"));
                Rol cliente = rolRepo.save(new Rol("Cliente"));

                usuarioRepo.save(usuarioService.crearUsuarioBasico("admin", "Admin", "admin@demo.com", "admin123", "10000001", adminSistema));
                usuarioRepo.save(usuarioService.crearUsuarioBasico("coordinador", "Coordinador", "coord@demo.com", "coord123", "10000002", coordinadorClinica));
                usuarioRepo.save(usuarioService.crearUsuarioBasico("vet", "Veterinario", "vet@demo.com", "vet123", "10000003", veterinario));
                usuarioRepo.save(usuarioService.crearUsuarioBasico("cliente", "Cliente", "cliente@demo.com", "cliente123", "10000004", cliente));

                System.out.println("Usuarios y roles precargados correctamente.");
            } else {
                System.out.println("Usuarios o roles ya existen. No se insertaron duplicados.");
            }
        };
    }
}
