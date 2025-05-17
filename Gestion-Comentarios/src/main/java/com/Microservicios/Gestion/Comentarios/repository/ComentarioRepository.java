package com.Microservicios.Gestion.Comentarios.repository;

import com.Microservicios.Gestion.Comentarios.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
