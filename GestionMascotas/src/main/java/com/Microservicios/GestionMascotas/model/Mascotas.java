package com.Microservicios.GestionMascotas.model;

import java.util.Date;

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
@Table(name = "mascotas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Mascotas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMascota;

    @Column(length = 3, nullable = false, unique= true )
    private Integer idUsuario;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String especie;

    @Column(length = 100, nullable = false)
    private String raza;

    @Column(length = 3)
    private Integer edad;

    @Column(length = 10, nullable = false)
    private String sexo;

    @Column(length = 5)
    private Integer pesoKg;

    @Column(length = 10, nullable = false)
    private Date fechaRegistro;

    
}