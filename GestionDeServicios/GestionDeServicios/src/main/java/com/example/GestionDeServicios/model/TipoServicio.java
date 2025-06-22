package com.example.GestionDeServicios.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tipo_servicio")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTipo;

    @Column(length = 30, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "tipoServicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Servicio> servicios;
}
