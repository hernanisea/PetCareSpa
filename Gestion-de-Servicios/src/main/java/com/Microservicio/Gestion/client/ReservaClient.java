package com.Microservicio.Gestion.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ReservaClient {

    private final RestTemplate restTemplate;

    public ReservaClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeReserva(Long idReserva) {
        try {
            String url = "http://localhost:8082/reservas/" + idReserva;
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
