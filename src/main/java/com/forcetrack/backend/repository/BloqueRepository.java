package com.forcetrack.backend.repository;

import com.forcetrack.backend.entity.Bloque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BloqueRepository extends JpaRepository<Bloque, Integer> {
    List<Bloque> findByUsuarioId(Integer usuarioId);
}
