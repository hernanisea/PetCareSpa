package com.Microservicio.Gestion.de.Reservas.client;



import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificacionClient {

    private final RestTemplate restTemplate;

    public NotificacionClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeNotificacion(Long idNotificacion) {
        try {
            String url = "http://localhost:8088/notificaciones/" + idNotificacion; // Asegúrate que coincida con el endpoint real
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
 


    
