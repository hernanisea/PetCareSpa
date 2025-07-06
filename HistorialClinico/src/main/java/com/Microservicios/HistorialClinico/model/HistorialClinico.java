package com.Microservicios.HistorialClinico.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historial_clinico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_historial", nullable = false)
    private Date fecha;

    @Column(length = 500)
    private String antecedentes;

    @Column(length = 500)
    private String comentarios;

    @Column(length = 500)
    private String diagnostico;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "mascota_id", nullable = false)
    private Long mascotaId;
} 
