package com.Microservicios.Gestion.de.Direcciones.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class UsuarioClient {

    private final WebClient webClient;

    public UsuarioClient(@Value("${usuario-service.url}") String usuarioServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(usuarioServiceUrl).build();
    }

    public Map<String, Object> getUsuarioById(Long id) {
        String token = "";

        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getCredentials() instanceof String credentials) {
            token = credentials;
        }

        System.out.println("TOKEN JWT CAPTURADO: " + token);

        return this.webClient.get()
                .uri("/{id}", id)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response ->
                    response.bodyToMono(String.class).flatMap(body -> {
                        System.err.println("Error del usuario-client: " + body);
                        return Mono.error(new RuntimeException("Usuario no encontrado: " + body));
                    })
                )
                .bodyToMono(Map.class)
                .block();
    }
}

