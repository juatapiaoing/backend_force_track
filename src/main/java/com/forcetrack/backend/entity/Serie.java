package com.forcetrack.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ejercicioId;

    private Double peso;

    private Integer repeticiones;

    private Integer rir;

    private Boolean completada;
}
