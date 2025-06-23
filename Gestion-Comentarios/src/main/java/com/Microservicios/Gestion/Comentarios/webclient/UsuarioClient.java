package com.Microservicios.Gestion.Comentarios.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

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
                .onStatus(status -> status.is4xxClientError(), response
                        -> response.bodyToMono(String.class).flatMap(body
                        -> // Puedes usar una excepción personalizada si quieres
                        Mono.error(new IllegalArgumentException("Usuario con ID " + id + " no existe."))
                )
                )
                .bodyToMono(Map.class)
                .block();
    }
}
