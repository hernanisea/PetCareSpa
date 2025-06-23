package com.Microservicios.GestionUsuarios.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DireccionClient {

    private final WebClient webClient;

    public DireccionClient(@Value("${direccion-service.url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Map<String, Object> obtenerDireccionPorId(Long id) {
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(), response ->
                response.bodyToMono(String.class).map(body ->
                    new RuntimeException("Dirección no encontrada")))
            .bodyToMono(Map.class)
            .block();
    }
}
