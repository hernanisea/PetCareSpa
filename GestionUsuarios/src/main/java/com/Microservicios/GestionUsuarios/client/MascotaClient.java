package com.Microservicios.GestionUsuarios.client;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MascotaClient {

    private final WebClient webClient;

    public MascotaClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8085/mascotas").build();
    }

    public List<Map<String, Object>> obtenerMascotasPorUsuario(Long idUsuario) {
        return this.webClient.get()
                .uri("/usuario/{idUsuario}", idUsuario)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .collectList()
                .block();
    }
    

}
