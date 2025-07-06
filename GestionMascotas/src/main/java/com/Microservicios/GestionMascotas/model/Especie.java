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

@Schema(name = "Especie", description = "Entidad que representa una especie animal")
@Entity
@Table(name = "especie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la especie", example = "1")
    @Column(name = "id_especie")
    private long idEspecie;

    @Column(name = "nombre", nullable = false, length = 35, unique = true)
    @Schema(description = "Nombre de la especie", example = "Canina")
    private String nombre;

    @OneToMany(mappedBy = "especie", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Mascotas> mascotas;
}