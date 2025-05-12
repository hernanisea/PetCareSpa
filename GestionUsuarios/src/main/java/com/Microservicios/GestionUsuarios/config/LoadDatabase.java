package com.Microservicios.GestionUsuarios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(RolRepository rolRepo, UsuarioRepository usuarioRepo) {
        return args -> {
            if (rolRepo.count() == 0 && usuarioRepo.count() == 0) {

                // Crear roles
                Rol adminSistema = rolRepo.save(new Rol("Administrador del Sistema"));
                Rol coordinadorClinica = rolRepo.save(new Rol("Coordinador de Clínica"));
                Rol veterinario = rolRepo.save(new Rol("Veterinario"));
                Rol gestorInventario = rolRepo.save(new Rol("Gestor de Inventario"));
                Rol cliente = rolRepo.save(new Rol("Cliente"));

                // Crear y guardar usuarios
                usuarioRepo.save(crearUsuario("admin", "Admin", "admin@demo.com", "admin123", "10000001", 1L, adminSistema));
                usuarioRepo.save(crearUsuario("coordinador", "Coordinador", "coord@demo.com", "coord123", "10000002", 2L, coordinadorClinica));
                usuarioRepo.save(crearUsuario("vet", "Veterinario", "vet@demo.com", "vet123", "10000003", 3L, veterinario));
                usuarioRepo.save(crearUsuario("inventario", "Inventario", "inv@demo.com", "inv123", "10000004", 4L, gestorInventario));
                usuarioRepo.save(crearUsuario("cliente", "Cliente", "cliente@demo.com", "cliente123", "10000005", 5L, cliente));

                System.out.println("✅ Roles y usuarios iniciales cargados.");
            } else {
                System.out.println("⚠️ Los datos ya existen. No se cargaron nuevos registros.");
            }
        };
    }

    private Usuario crearUsuario(String nombre, String apellido, String correo, String clave,
                                 String telefono, Long idDireccion, Rol rol) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setClave(clave);  // Aquí podrías usar encriptación en el futuro
        usuario.setEstado(true);
        usuario.setTelefono(telefono);
        usuario.setIdDireccion(idDireccion);
        usuario.setRol(rol);
        return usuario;
    }
}
