package com.Microservicios.GestionUsuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Microservicios.GestionUsuarios.dto.JwtResponse;
import com.Microservicios.GestionUsuarios.dto.LoginRequest;
import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;
import com.Microservicios.GestionUsuarios.security.JwtUtil;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        
        Usuario usuario = usuarioRepository.findAll().stream()
                .filter(u -> u.getCorreo().equals(request.getCorreo()))
                .findFirst()
                .orElse(null);

       
        if (usuario == null || !passwordEncoder.matches(request.getClave(), usuario.getClave())) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

    
        String token = jwtUtil.generateToken(usuario.getCorreo());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
