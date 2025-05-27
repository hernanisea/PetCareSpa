package com.Microservicios.GestionUsuarios.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class HistorialClinicoClient {

    private final RestTemplate restTemplate;

    public HistorialClinicoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeHistorial(Long idHistorial) {
        try {
            String url = "http://localhost:8085/historial/" + idHistorial; // Ajusta si el endpoint es diferente
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}