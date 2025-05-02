package com.Microservicios.GestionUsuarios.model;

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
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdUsuario;

    @Column(length = 100, nullable = false, unique = true)
    private String nombreCompleto;
    
    @Column(length = 200, nullable = false)
    private String correo;

    @Column(nullable= false)
    private String passwordHash;

    @Column(length = 20, nullable = false)
    private String rol;//cliente, veterinario, admin
    
    @Column(length = 15,nullable = true)
    private String telefono;


}
