package com.forcetrack.backend.repository;

import com.forcetrack.backend.entity.Dia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiaRepository extends JpaRepository<Dia, Integer> {
    List<Dia> findByBloqueId(Integer bloqueId);
}
