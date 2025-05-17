package com.Microservicios.Gestion.Comentarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comentarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long id;

    @Column(name = "contenido", nullable = false, length = 500)
    private String contenido;

    @Column(name = "fecha_creacion", nullable = false)
    private String fechaCreacion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "id_reserva", nullable = false)
    private Long idReserva;
}
