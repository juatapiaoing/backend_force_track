package com.forcetrack.backend.controller;

import com.forcetrack.backend.entity.Usuario;
import com.forcetrack.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            return ResponseEntity.badRequest().body("El correo ya existe");
        }
        Usuario saved = usuarioRepository.save(usuario);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String correo = credentials.get("correo");
        String contrasena = credentials.get("contrasena");
        
        return usuarioRepository.findByCorreo(correo)
                .filter(u -> u.getContrasena().equals(contrasena))
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.status(401).build());
    }
}
