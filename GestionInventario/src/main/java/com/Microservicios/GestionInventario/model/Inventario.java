package com.Microservicios.GestionInventario.model;

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
@Entity
@Table(name = "Inventario")
@AllArgsConstructor
@NoArgsConstructor

public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idProducto;
    
    @Column(name = "nombre_producto", length = 150)
    private String nombreProducto;

    @Column(length= 300)
    private String descripcion;

    @Column(length= 100, nullable = false)
    private Integer stock;

    @Column(length= 20, nullable = false)
    private String unidadMedida;

    @Column(length= 10, nullable = false)
    private Integer stockMinimo;

    @Column(length= 10, nullable = false)
    private Integer precio;

    @Column(name = "fecha_ingreso", length= 10, nullable = false)
    private Date fechaIngreso;


}
