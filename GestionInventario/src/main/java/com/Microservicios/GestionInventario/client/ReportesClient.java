package com.Microservicios.GestionInventario.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ReportesClient {

    private final RestTemplate restTemplate;

    public ReportesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @GetMapping("/reportes")
    public boolean existeReporte(Long idReportes) {
        try {
            String url = "http://localhost:8089/reportes/" + idReportes; 
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
