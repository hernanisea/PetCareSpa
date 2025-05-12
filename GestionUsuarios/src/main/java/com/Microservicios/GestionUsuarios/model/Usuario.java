package com.Microservicios.GestionUsuarios.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
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

    @Column(name = "id_direccion", nullable = false)
    private Long idDireccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_rol"))
    private Rol rol;
}
