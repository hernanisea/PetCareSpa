package com.Microservicios.HistorialClinico.webclient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class TratamientoClient {

    private final RestTemplate restTemplate;

    public TratamientoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeTratamiento(Long idTratamiento) {
        try {
            String url = "http://localhost:8084/tratamientos/" + idTratamiento; // Ajusta el puerto/endpoint real si es diferente
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}