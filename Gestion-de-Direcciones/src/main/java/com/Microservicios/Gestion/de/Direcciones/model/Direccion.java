package com.Microservicios.Gestion.de.Direcciones.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "direcciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa la dirección de un usuario")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la dirección", example = "100")
    private Long idDireccion;

    @Column(nullable = false, length = 40)
    @Schema(description = "Nombre de la calle", example = "Av. Siempre Viva 742")
    private String calle;

    @Column(length = 100)
    @Schema(description = "Descripción adicional (ej. edificio, piso)", example = "Departamento 3B, Edificio Central")
    private String descripcion;

    @Column(nullable = false)
    @Schema(description = "Código postal", example = "8320000")
    private int codigoPostal;

    @ManyToOne
    @JoinColumn(name = "id_comuna", nullable = false)
    @Schema(description = "Comuna donde se ubica la dirección")
    private Comuna comuna;

    @Column(nullable = false)
    @Schema(description = "ID del usuario asociado a esta dirección", example = "5")
    private Long usuarioId;
}
