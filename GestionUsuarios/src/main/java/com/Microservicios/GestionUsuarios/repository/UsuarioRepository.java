package com.Microservicios.GestionUsuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservicios.GestionUsuarios.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCorreo(String correo);
    Usuario findByCorreo(String correo);
}
