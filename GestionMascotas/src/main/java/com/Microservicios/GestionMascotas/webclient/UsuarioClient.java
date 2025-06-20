package com.Microservicios.GestionMascotas.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UsuarioClient {

    private final WebClient webClient;

    public UsuarioClient(@Value("${usuario-service.url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public void validarUsuarioExiste(Long idUsuario) {
        webClient.get()
                .uri("/{id}", idUsuario)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(error -> {
                                    throw new RuntimeException("Usuario no encontrado");
                                }))
                .bodyToMono(Void.class)
                .block();
    }
}