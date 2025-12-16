package com.forcetrack.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bloques")
public class Bloque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer usuarioId;

    private String nombre;
    
    private String categoria;
}
