package com.Microservicios.GestionUsuarios.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "UsuarioEntity", description = "Entidad JPA que representa a un usuario del sistema")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del usuario", example = "1")
    private Long idUsuario;

    @Schema(description = "Nombre del usuario", example = "Juan")
    private String nombre;

    @Schema(description = "Apellido del usuario", example = "Pérez")
    private String apellido;

    @Column(unique = true, nullable = false)
    @Schema(description = "Correo electrónico del usuario", example = "juan.perez@demo.com")
    private String correo;

    @Column(nullable = false)
    @Schema(hidden = true)
    private String clave;

    @Schema(description = "Estado del usuario", example = "true")
    private Boolean estado;

    @Schema(description = "Número de teléfono", example = "987654321")
    private String telefono;

    @Embedded
    @Schema(hidden = true)
    private RelacionesUsuario relaciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_rol"))
    @com.fasterxml.jackson.annotation.JsonBackReference
    @Schema(description = "Rol asignado al usuario")
    private Rol rol;
}
