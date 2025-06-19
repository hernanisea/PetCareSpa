package com.Microservicio.Gestion.de.Reservas.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MascotaClient {

    private final WebClient webClient;

    public MascotaClient(@Value("${mascota-service.url}") String url) {
        this.webClient = WebClient.builder().baseUrl(url).build();
    }

    public Map<String, Object> getMascotaById(Long id) {
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(), response ->
                response.bodyToMono(String.class).map(body -> new RuntimeException("Mascota no encontrada")))
            .bodyToMono(Map.class)
            .block();
    }
}
