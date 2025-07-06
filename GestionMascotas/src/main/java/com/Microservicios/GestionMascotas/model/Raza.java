package com.Microservicios.GestionMascotas.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Raza", description = "Entidad que representa una raza animal")
@Entity
@Table(name = "raza")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Raza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la raza", example = "5")
    @Column(name = "id_raza")
    private long idRaza;

    @Column(name = "nombre", nullable = false, length = 35, unique = true)
    @Schema(description = "Nombre de la raza", example = "Pastor Alemán")
    private String nombre;

    @OneToMany(mappedBy = "raza", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Mascotas> mascotas;
}
