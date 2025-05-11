package com.Microservicios.Gestion.de.Direcciones.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "direcciones")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idDireccion;
    
    @Column(nullable= false,length= 40)
    private String calle;
    @Column(length = 100)
    private String descripcion;
    @Column(nullable= false)
    private int codigoPostal;

    private Long idComuna;


}
