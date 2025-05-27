package com.Microservicios.GestionUsuarios.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class MascotaClient {

    private final RestTemplate restTemplate;

    public MascotaClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeMascota(Long idMascota) {
        try {
            String url = "http://localhost:8086/mascotas/" + idMascota; // Asegúrate que esta ruta existe en GestionMascotas
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
