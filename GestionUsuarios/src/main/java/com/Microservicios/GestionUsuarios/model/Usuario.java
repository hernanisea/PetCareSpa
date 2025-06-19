package com.Microservicios.GestionUsuarios.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "clave", nullable = false)
    private String clave;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(nullable = false)
    private Long idDireccion;

    @Column(nullable = false)
    private Long idMascota;

    @Column(nullable = false)
    private Long idComentario;

    @Column (nullable = false)
    private Long idNotificacion;
    
    @Column (nullable = false)
    private Long idHistorial;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_rol"))
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Rol rol;

}
