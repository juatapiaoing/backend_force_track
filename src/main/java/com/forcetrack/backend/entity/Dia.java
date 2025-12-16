package com.forcetrack.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "dias")
public class Dia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer bloqueId;

    private Integer numeroSemana;

    private LocalDate fecha;

    private String nombre;

    private String notas;
}
