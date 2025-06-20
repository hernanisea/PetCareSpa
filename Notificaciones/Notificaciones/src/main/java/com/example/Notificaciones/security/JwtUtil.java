package com.example.Notificaciones.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    public String extraerCorreoDesdeToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes()) // si usas string base64
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();

        return claims.getSubject(); // generalmente el correo o nombre de usuario va como "sub"
    }
}
