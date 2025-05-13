package com.Microservicios.GestionMascotas.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name= "especie")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEspecie;

    @Column(name = "nombre", nullable = false, length = 35, unique = true)
    private String nombre;
     
    @OneToMany(mappedBy = "especie", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private List<Mascotas> mascotas;

}
