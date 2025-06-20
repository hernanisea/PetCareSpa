package com.Microservicios.GestionUsuarios.controller;

import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;
import com.Microservicios.GestionUsuarios.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    // Buscar usuario por correo
    Usuario usuario = usuarioRepository.findAll().stream()
            .filter(u -> u.getCorreo().equals(request.correo))  
            .findFirst()
            .orElse(null);

    // Validar usuario y contraseña
    if (usuario == null || !passwordEncoder.matches(request.clave, usuario.getClave())) {
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

    // Generar token
    String token = jwtUtil.generateToken(usuario.getCorreo());
    return ResponseEntity.ok(new JwtResponse(token));
}


    // DTOs internos
    public static class LoginRequest {
        public String correo;
        public String clave;
    }

    public static class JwtResponse {
        public String token;

        public JwtResponse(String token) {
            this.token = token;
        }
    }
}
