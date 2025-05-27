package com.Microservicios.Reportes.model;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Reportes")

public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReportes;

    @Column(name = "titulo", nullable=true)
    private String titulo;

    @Column(length=200)
    private String descripcion;

    @Column(nullable=true)
    private Date fechaCreacion;

}
