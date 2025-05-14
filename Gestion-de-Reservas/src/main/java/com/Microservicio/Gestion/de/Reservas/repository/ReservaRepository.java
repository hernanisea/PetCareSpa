package com.Microservicio.Gestion.de.Reservas.repository;

import com.Microservicio.Gestion.de.Reservas.model.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reservas, Long> {
}
