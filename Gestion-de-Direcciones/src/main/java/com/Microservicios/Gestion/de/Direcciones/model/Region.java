package com.Microservicios.Gestion.de.Direcciones.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Region")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa una región geográfica")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la región", example = "1")
    private Long idRegion;

    @Column(nullable = false, length = 30)
    @Schema(description = "Nombre de la región", example = "Región Metropolitana")
    private String nombre;
}
