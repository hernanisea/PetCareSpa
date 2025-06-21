package com.example.Notificaciones.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "notificacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificacion;

    @Column(name = "usuarioId", length = 20)
    private Long usuarioId;

    @Column(nullable = false, length = 255)
    private String mensaje;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(nullable = false)
    private boolean leido;

    @Column(name = "creado_por", length = 100)
    private String creadoPor;
}
