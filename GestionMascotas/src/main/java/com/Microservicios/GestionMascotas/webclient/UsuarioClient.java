package com.Microservicios.GestionMascotas.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UsuarioClient {

    private final WebClient webClient;

    public UsuarioClient(@Value("${usuario-service.url}") String baseUrl) {
        this.webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    public void validarUsuarioExiste(Long idUsuario) {
        String token = obtenerTokenActual();

        webClient.get()
            .uri("/{id}", idUsuario)
            .headers(headers -> headers.setBearerAuth(token)) // Agrega el token como Authorization: Bearer
            .retrieve()
            .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                    .flatMap(error -> Mono.error(new RuntimeException("Usuario no encontrado"))))
            .bodyToMono(Void.class)
            .block();
    }

    private String obtenerTokenActual() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof String token) {
            return token;
        }
        throw new RuntimeException("Token JWT no disponible en el contexto de seguridad");
    }
}
