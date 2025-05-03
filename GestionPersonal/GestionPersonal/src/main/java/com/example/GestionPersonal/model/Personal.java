package com.example.GestionPersonal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idPersonal;

    @Column(name = "nombre_completo", length = 150, nullable = false)
    private String nombreCompleto;

    @Column(length = 100, nullable = false)
    private String especialidad;

    @Column(length = 20, nullable = false)
    private String telefono;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(name = "horario_disponible", columnDefinition = "TEXT")
    private String horarioDisponible; 

    @Column(nullable = false)
    private Boolean estado; 
}
