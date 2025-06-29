package com.example.Notificaciones.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class UsuarioClient {

    private final WebClient webClient;

    public UsuarioClient(@Value("${usuario-service.url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Map<String, Object> obtenerUsuarioPorId(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}