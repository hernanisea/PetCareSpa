package com.Microservicios.GestionUsuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservicios.GestionUsuarios.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // ✅ Verifica si existe un usuario por correo (booleano)
    boolean existsByCorreo(String correo);

    // ✅ Devuelve un Optional<Usuario> por correo
    Usuario findByCorreo(String correo);
}
