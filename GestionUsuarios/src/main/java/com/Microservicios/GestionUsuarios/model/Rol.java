package com.Microservicios.GestionUsuarios.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Rol", description = "Entidad que representa un rol del sistema")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    @Schema(hidden = true)
    private List<Usuario> usuarios;
}
