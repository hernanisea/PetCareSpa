package com.Microservicios.GestionUsuarios.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class DireccionClient {

    private final RestTemplate restTemplate;

    public DireccionClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeDireccion(Long idDireccion) {
        try {
            String url = "http://localhost:8082/direcciones/" + idDireccion;
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}