package com.example.GestionDeServicios.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Servicio", description = "Entidad que representa un servicio disponible en el sistema")
@Entity
@Table(name = "servicio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    @Schema(description = "ID único del servicio", example = "1")
    private Long idServicio;

    @Column(name = "nombre", nullable = false)
    @Schema(description = "Nombre del servicio", example = "Vacunación anual")
    private String nombre;

    @Column(name = "descripcion", length = 100, nullable = false)
    @Schema(description = "Descripción breve del servicio", example = "Aplicación de vacuna anual contra enfermedades comunes")
    private String descripcion;

    @Column(name = "precio", nullable = false)
    @Schema(description = "Precio del servicio", example = "15000")
    private Integer precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo", nullable = false, foreignKey = @ForeignKey(name = "FK_servicio_tipoServicio"))
    @JsonBackReference
    @Schema(description = "Tipo de servicio al que pertenece")
    private TipoServicio tipoServicio;
}
