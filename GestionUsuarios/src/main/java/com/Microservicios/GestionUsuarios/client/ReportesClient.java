package com.Microservicios.GestionUsuarios.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ReportesClient {

    private final RestTemplate restTemplate;

    public ReportesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeReporte(Long idReportes) {
        try {
            String url = "http://localhost:8089/reportes/" + idReportes; // Asegúrate que coincida con tu endpoint real
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
