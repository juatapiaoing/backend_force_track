package com.forcetrack.backend.controller;

import com.forcetrack.backend.entity.Bloque;
import com.forcetrack.backend.repository.BloqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloque")
public class BloqueController {

    @Autowired
    private BloqueRepository bloqueRepository;

    @GetMapping
    public List<Bloque> getBloques(@RequestParam(required = false) Integer usuario_id) {
        if (usuario_id != null) {
            return bloqueRepository.findByUsuarioId(usuario_id);
        }
        return bloqueRepository.findAll();
    }

    @PostMapping
    public Bloque createBloque(@RequestBody Bloque bloque) {
        return bloqueRepository.save(bloque);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Bloque> getBloque(@PathVariable Integer id) {
        return bloqueRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
