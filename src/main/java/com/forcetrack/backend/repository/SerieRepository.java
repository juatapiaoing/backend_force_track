package com.forcetrack.backend.repository;

import com.forcetrack.backend.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SerieRepository extends JpaRepository<Serie, Integer> {
    List<Serie> findByEjercicioId(Integer ejercicioId);
}
