package com.example.GestionDeServicios.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long idServicio;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo", nullable = false, foreignKey = @ForeignKey(name = "FK_servicio_tipoServicio"))
    @JsonBackReference
    private TipoServicio tipoServicio;
}
