package com.Microservicios.GestionUsuarios.client;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DireccionClient {

    private final WebClient webClient;

    public DireccionClient(@Value("${direccion-service.url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public List<Map<String, Object>> obtenerDireccionesPorUsuario(Long idUsuario) {
        return this.webClient.get()
            .uri("/usuario/{idUsuario}", idUsuario)
            .retrieve()
            .bodyToFlux(new ParameterizedTypeReference<Map<String, Object>>() {})
            .collectList()
            .block();
    }
}
