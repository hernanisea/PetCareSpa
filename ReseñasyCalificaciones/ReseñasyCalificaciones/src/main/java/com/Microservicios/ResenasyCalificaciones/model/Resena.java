package com.Microservicios.ResenasyCalificaciones.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reseñas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resena {
    
    @Id
    private long id;
    
    @Column(length= 5, nullable= false)
    private long clienteId;
    
    @Column(length= 5, nullable= false)
    private long veterinarioId;
    
    @Column(length= 100)
    private String comentario;

    @Column(length= 1)
    private Integer calificacion;
    
    @Column(length= 10, nullable= false)
    private LocalDateTime fecha;

}
