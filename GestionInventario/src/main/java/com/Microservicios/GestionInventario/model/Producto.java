package com.Microservicios.GestionInventario.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Producto", description = "Entidad que representa un producto en el inventario")
@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    @Schema(description = "ID único del producto", example = "1")
    private Long idProducto;

    @Column(name = "nombre", nullable = false)
    @Schema(description = "Nombre del producto", example = "Antibiótico oral")
    private String nombre;

    @Column(name = "descrip", nullable = false)
    @Schema(description = "Descripción detallada del producto", example = "Medicamento para infecciones bacterianas")
    private String descripcion;

    @Column(name = "stock", nullable = false)
    @Schema(description = "Cantidad disponible en stock", example = "100")
    private Integer stock;

    @Column(name = "stock_minimo", nullable = false)
    @Schema(description = "Cantidad mínima requerida antes de emitir una alerta", example = "20")
    private Integer stockMinimo;

    @Column(name = "precio", nullable = false)
    @Schema(description = "Precio unitario del producto", example = "19990.50")
    private Double precio;
}
