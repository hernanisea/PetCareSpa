package com.Microservicios.GestionInventario.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

@Schema(name = "Tratamiento", description = "Entidad que representa un tratamiento veterinario aplicado usando productos")
@Entity
@Table(name = "tratamiento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamiento")
    @Schema(description = "ID único del tratamiento", example = "101")
    private Long idTratamiento;

    @Column(name = "cantidad", nullable = false)
    @Schema(description = "Cantidad de producto usada en el tratamiento", example = "2")
    private Integer cantidad;

    @Column(name = "subtotal", nullable = false)
    @Schema(description = "Subtotal del tratamiento en pesos chilenos", example = "39980.0")
    private Double subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name = "FK_tratamiento_producto"))
    @JsonBackReference
    @Schema(description = "Producto asociado al tratamiento")
    private Producto producto;
}
