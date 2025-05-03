package com.example.GestionPersonal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GestionPersonal.model.Personal;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
