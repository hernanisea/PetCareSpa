package com.example.Notificaciones.security;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // Usa exactamente la misma clave secreta que en GestiónUsuarios
    private final String SECRET_KEY = "clave-super-segura-para-jwt-2025-clinicavetsystem";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extraerCorreoDesdeToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // Elimina el prefijo "Bearer "
        }

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // El correo fue guardado en el 'subject' del token
    }
}
