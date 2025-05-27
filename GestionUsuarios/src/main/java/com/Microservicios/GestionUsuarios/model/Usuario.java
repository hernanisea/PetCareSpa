package com.Microservicios.GestionUsuarios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "id_direccion")
    private Long idDireccion;

    @Column(name = "id_mascota")
    private Long idMascota;

    @Column(name = "id_comentario")
    private Long idComentario;

    @Column (name = "id_notificaciones")
    private Long idNotificacion;
    
    @Column (name = "id_reportes")
    private Long idReportes;

    @Column (name = "id_historial_clinico")
    private Long idHistorial;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_rol"))
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Rol rol;

}
