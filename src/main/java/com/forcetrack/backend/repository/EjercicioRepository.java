package com.forcetrack.backend.repository;

import com.forcetrack.backend.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {
    List<Ejercicio> findByDiaId(Integer diaId);
}
