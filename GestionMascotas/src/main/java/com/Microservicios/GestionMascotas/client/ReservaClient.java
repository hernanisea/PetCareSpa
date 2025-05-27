package com.Microservicios.GestionMascotas.client;

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
            String url = "http://localhost:8089/reservas/" + idReserva; // Asegúrate de que el endpoint coincida
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}