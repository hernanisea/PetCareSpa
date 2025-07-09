package com.example.Notificaciones.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UsuarioClient {

    private final WebClient webClient;

    public UsuarioClient(@Value("${usuario-service.url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Map<String, Object> obtenerUsuarioPorId(Long id) {
        String jwt = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        return this.webClient.get()
                .uri("/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}
