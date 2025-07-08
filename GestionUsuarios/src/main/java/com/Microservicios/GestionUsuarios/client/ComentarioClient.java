package com.Microservicios.GestionUsuarios.client;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ComentarioClient {

    private final WebClient webClient;

    public ComentarioClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8080/comentarios").build();
    }

    public List<Map<String, Object>> obtenerComentariosPorUsuario(Long idUsuario) {
        return this.webClient.get()
            .uri("/usuario/{idUsuario}", idUsuario)
            .retrieve()
            .bodyToFlux(new ParameterizedTypeReference<Map<String, Object>>() {})
            .collectList()
            .block();
    }
}
