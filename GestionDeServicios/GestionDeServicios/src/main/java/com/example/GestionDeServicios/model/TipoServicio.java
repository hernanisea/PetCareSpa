package com.example.GestionDeServicios.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TipoServicio", description = "Entidad que representa una categoría o tipo de servicio")
@Entity
@Table(name = "tipo_servicio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del tipo de servicio", example = "1")
    private long idTipo;

    @Column(length = 30, nullable = false)
    @Schema(description = "Nombre del tipo de servicio", example = "Consultas")
    private String nombre;

    @OneToMany(mappedBy = "tipoServicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @Schema(description = "Lista de servicios asociados a este tipo")
    private List<Servicio> servicios;
}
