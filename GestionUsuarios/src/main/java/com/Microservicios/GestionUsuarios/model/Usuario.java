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
    private Long idUsuario;

    private String nombre;
    private String apellido;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String clave;

    private Boolean estado;

    private String telefono;

    // ⚠️ Campos opcionales marcados como nullable = true
    @Column(name = "id_direccion", nullable = true)
    private Long idDireccion;

    @Column(name = "id_mascota", nullable = true)
    private Long idMascota;

    @Column(name = "id_comentario", nullable = true)
    private Long idComentario;

    @Column(name = "id_notificacion", nullable = true)
    private Long idNotificacion;

    @Column(name = "id_reportes", nullable = true)
    private Long idReportes;

    @Column(name = "id_historial", nullable = true)
    private Long idHistorial;


    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_rol"))
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Rol rol;

}
