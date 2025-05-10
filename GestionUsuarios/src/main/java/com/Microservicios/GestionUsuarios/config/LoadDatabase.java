package com.Microservicios.GestionUsuarios.config;

import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(RolRepository rolRepo, UsuarioRepository usuarioRepo) {
        return args -> {
            if (rolRepo.count() == 0 && usuarioRepo.count() == 0) {

                // Crear roles
                Rol adminSistema = rolRepo.save(new Rol(null, "Administrador del Sistema", null));
                Rol coordinadorClinica = rolRepo.save(new Rol(null, "Coordinador de Clínica", null));
                Rol veterinario = rolRepo.save(new Rol(null, "Veterinario", null));
                Rol gestorInventario = rolRepo.save(new Rol(null, "Gestor de Inventario", null));
                Rol cliente = rolRepo.save(new Rol(null, "Cliente", null));

                // Usuario Admin
                Usuario admin = new Usuario();
                admin.setNombre("admin");
                admin.setApellido("Admin");
                admin.setCorreo("admin@demo.com");
                admin.setClave("admin123");
                admin.setEstado(true);
                admin.setTelefono("10000001");
                admin.setIdDireccion(1L);
                admin.setRol(adminSistema);
                usuarioRepo.save(admin);

                // Usuario Coordinador
                Usuario coord = new Usuario();
                coord.setNombre("coordinador");
                coord.setApellido("Coordinador");
                coord.setCorreo("coord@demo.com");
                coord.setClave("coord123");
                coord.setEstado(true);
                coord.setTelefono("10000002");
                coord.setIdDireccion(2L);
                coord.setRol(coordinadorClinica);
                usuarioRepo.save(coord);

                // Usuario Veterinario
                Usuario vet = new Usuario();
                vet.setNombre("vet");
                vet.setApellido("Veterinario");
                vet.setCorreo("vet@demo.com");
                vet.setClave("vet123");
                vet.setEstado(true);
                vet.setTelefono("10000003");
                vet.setIdDireccion(3L);
                vet.setRol(veterinario);
                usuarioRepo.save(vet);

                // Usuario Inventario
                Usuario inv = new Usuario();
                inv.setNombre("inventario");
                inv.setApellido("Inventario");
                inv.setCorreo("inv@demo.com");
                inv.setClave("inv123");
                inv.setEstado(true);
                inv.setTelefono("10000004");
                inv.setIdDireccion(4L);
                inv.setRol(gestorInventario);
                usuarioRepo.save(inv);

                // Usuario Cliente
                Usuario cli = new Usuario();
                cli.setNombre("cliente");
                cli.setApellido("Cliente");
                cli.setCorreo("cliente@demo.com");
                cli.setClave("cliente123");
                cli.setEstado(true);
                cli.setTelefono("10000005");
                cli.setIdDireccion(5L);
                cli.setRol(cliente);
                usuarioRepo.save(cli);

                System.out.println("✅ Roles y usuarios iniciales cargados.");
            } else {
                System.out.println("⚠️ Los datos ya existen. No se cargaron nuevos registros.");
            }
        };
    }
}
