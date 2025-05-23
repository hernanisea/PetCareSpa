package com.Microservicios.GestionUsuarios.repository;

import com.Microservicios.GestionUsuarios.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}
