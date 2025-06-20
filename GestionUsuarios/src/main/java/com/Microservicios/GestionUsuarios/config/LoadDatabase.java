package com.Microservicios.GestionUsuarios.config;

import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadDatabase {

@Bean
CommandLineRunner initDatabase(RolRepository rolRepo, UsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder) {
    return args -> {

        if (rolRepo.count() == 0 && usuarioRepo.count() == 0) {
            Rol adminSistema = rolRepo.save(new Rol("Administrador del Sistema"));
            Rol coordinadorClinica = rolRepo.save(new Rol("Coordinador de Clínica"));
            Rol veterinario = rolRepo.save(new Rol("Veterinario"));
            Rol cliente = rolRepo.save(new Rol("Cliente"));

            usuarioRepo.save(crearUsuario("admin", "Admin", "admin@demo.com", "admin123", "10000001", adminSistema, passwordEncoder));
            usuarioRepo.save(crearUsuario("coordinador", "Coordinador", "coord@demo.com", "coord123", "10000002", coordinadorClinica, passwordEncoder));
            usuarioRepo.save(crearUsuario("vet", "Veterinario", "vet@demo.com", "vet123", "10000003", veterinario, passwordEncoder));
            usuarioRepo.save(crearUsuario("cliente", "Cliente", "cliente@demo.com", "cliente123", "10000004", cliente, passwordEncoder));

            System.out.println("✅ Usuarios y roles precargados correctamente.");
        } else {
            System.out.println("⚠️ Usuarios o roles ya existen. No se insertaron duplicados.");
        }
    };
}

private Usuario crearUsuario(String nombre, String apellido, String correo, String clave, String telefono, Rol rol, PasswordEncoder encoder) {
    Usuario usuario = new Usuario();
    usuario.setNombre(nombre);
    usuario.setApellido(apellido);
    usuario.setCorreo(correo);
    usuario.setClave(encoder.encode(clave));
    usuario.setTelefono(telefono);
    usuario.setEstado(true);
    usuario.setRol(rol);

    // Evitar nulls en campos opcionales
    usuario.setIdComentario(0L);
    usuario.setIdDireccion(0L);
    usuario.setIdMascota(0L);
    usuario.setIdNotificacion(0L);
    usuario.setIdReportes(0L);
    usuario.setIdHistorial(0L);

    return usuario;
}
}
