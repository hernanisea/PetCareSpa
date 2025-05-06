package com.Microservicios.GestionCitas.model;

import java.util.Date;

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
@Table(name= "citas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCita;

    @Column (nullable= false)
    private Date fechaHora;

    private long clienteId;
    
    private long veterinarioId;

    @Column(length = 100)
    private String motivo;

    @Column(length = 20, nullable = false)
    private String estado;

    public Object map(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }
}
