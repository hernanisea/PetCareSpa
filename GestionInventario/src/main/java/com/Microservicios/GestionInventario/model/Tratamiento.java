package com.Microservicios.GestionInventario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tratamiento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamiento")
    private Long idTratamiento;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name = "FK_tratamiento_producto"))
    private Producto producto;
}
