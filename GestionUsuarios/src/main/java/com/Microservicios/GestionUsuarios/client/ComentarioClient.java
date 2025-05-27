package com.Microservicios.GestionUsuarios.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ComentarioClient {

    private final RestTemplate restTemplate;

    public ComentarioClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeComentario(Long idComentario) {
        try {
            String url = "http://localhost:8080/comentarios/" + idComentario; 
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}