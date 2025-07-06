package com.Microservicios.Gestion.de.Direcciones.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Comuna")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa una comuna dentro de una región")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la comuna", example = "10")
    private Long idComuna;

    @Column(nullable = false, length = 30)
    @Schema(description = "Nombre de la comuna", example = "Santiago")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_region", nullable = false)
    @Schema(description = "Región a la que pertenece la comuna")
    private Region region;
}
