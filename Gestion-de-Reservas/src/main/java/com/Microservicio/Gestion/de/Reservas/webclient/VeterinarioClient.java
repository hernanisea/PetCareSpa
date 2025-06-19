package com.Microservicio.Gestion.de.Reservas.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class VeterinarioClient {
    private final WebClient webClient;

    //metodo constructor de la clase
    public VeterinarioClient(@Value("${veterinario-service.url}") String clienteServiceUrl){
        this.webClient = WebClient.builder().baseUrl(clienteServiceUrl).build();

    }
    //metodo para comunicarnos con el microservicio de cliente
    //y buscar si un cliente existe mediante su id
    public Map<String, Object> getVeterinarioById(Long id){
        return this.webClient.get()
        .uri("/{id}",id).retrieve()
        .onStatus(status -> status.is4xxClientError(), response -> response.bodyToMono(String.class)
        .map(body -> new RuntimeException("Veterinario no encontrado"))).bodyToMono(Map.class).block();
    }
}
