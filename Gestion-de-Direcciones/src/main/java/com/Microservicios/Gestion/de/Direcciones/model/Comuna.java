package com.Microservicios.Gestion.de.Direcciones.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Comuna")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComuna;

    @Column(nullable = false, length = 30)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_region", nullable = false)
    private Region region; // ✔️ relación correcta
}

