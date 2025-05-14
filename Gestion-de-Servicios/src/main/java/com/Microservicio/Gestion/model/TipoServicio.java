package com.Microservicio.Gestion.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_servicio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTipo;

    @Column(length=30, nullable=false)
    private String nombre;

    @OneToMany(mappedBy = "tipoServicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Servicio> servicio;
}