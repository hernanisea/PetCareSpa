package com.Microservicios.SoporteTecnico.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "soporte_tecnico")

public class TicketSoporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTicket;

    private Long usuarioId;
    
    @Enumerated(EnumType.STRING)
    private TipoTicket tipo;
    
    @Column
    private String descripcion;
    
    @Column
    private LocalDateTime fechaCreacion;
    
    @Enumerated(EnumType.STRING)
    private EstadoTicket estado;
    
    @Enumerated(EnumType.STRING)
    private PrioridadTicket prioridad;
    
    @Column
    private String respuesta;

    public enum TipoTicket {
        ERROR_TECNICO, DUDA_FUNCIONAL, RECLAMO, SUGERENCIA
    }

    public enum EstadoTicket {
        ABIERTO, EN_PROCESO, RESUELTO, CERRADO
    }

    public enum PrioridadTicket {
        BAJA, MEDIA, ALTA
    }

}
