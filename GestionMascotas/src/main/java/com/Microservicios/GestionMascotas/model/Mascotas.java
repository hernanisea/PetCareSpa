package com.Microservicios.GestionMascotas.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Mascotas", description = "Entidad que representa una mascota registrada por un usuario")
@Entity
@Table(name = "mascotas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mascotas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la mascota", example = "10")
    private Long idMascota;

    @Schema(description = "ID del usuario dueño de la mascota", example = "3")
    @Column(name= "id_usuario",nullable = false)
    private Long idUsuario;

    @Schema(description = "Nombre de la mascota", example = "Luna")
    @Column(length = 100, nullable = false)
    private String nombre;

    @Schema(description = "Edad de la mascota", example = "4")
    @Column(length = 3)
    private Integer edad;

    @Schema(description = "Sexo de la mascota", example = "Hembra")
    @Column(length = 10, nullable = false)
    private String sexo;

    @Schema(description = "Peso de la mascota en kilogramos", example = "12")
    @Column(length = 5)
    private Integer pesoKg;

    @Schema(description = "Fecha en la que se registró la mascota", example = "2025-07-06")
    @Column(length = 10, nullable = false)
    private Date fechaRegistro;

    @Schema(description = "ID de la reserva asociada (si aplica)", example = "null")
    @Column(name = "id_reserva")
    private Long idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie", nullable = false, foreignKey = @ForeignKey(name = "FK_mascotas_especie"))
    @JsonBackReference
    private Especie especie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_raza", nullable = false, foreignKey = @ForeignKey(name = "FK_mascotas_raza"))
    @JsonBackReference
    private Raza raza;
}
