package com.example.Notificaciones.service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.Notificaciones.client.UsuarioClient;
import com.example.Notificaciones.dto.NotificacionRequest;
import com.example.Notificaciones.model.Notificacion;
import com.example.Notificaciones.repository.NotificacionRepository;

public class NotificacionServiceTest {

    private NotificacionRepository notificacionRepository;
    private UsuarioClient usuarioClient;
    private NotificacionService service;

    @BeforeEach
    void setUp() {
        notificacionRepository = mock(NotificacionRepository.class);
        usuarioClient = mock(UsuarioClient.class);
        service = new NotificacionService();
        service.notificacionRepository = notificacionRepository;
        service.usuarioClient = usuarioClient;
    }

    @Test
    void testCrearDesdeDTO() {
        NotificacionRequest req = new NotificacionRequest(1L, "Mensaje Test");
        when(usuarioClient.obtenerUsuarioPorId(1L)).thenReturn(Map.of("id", 1L));
        when(notificacionRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Notificacion resultado = service.crearDesdeDTO(req, "creador@correo.com");

        assertThat(resultado.getUsuarioId()).isEqualTo(1L);
        assertThat(resultado.getCreadoPor()).isEqualTo("creador@correo.com");
    }

    @Test
    void testMarcarComoLeida() {
        Notificacion noti = new Notificacion(1L, 2L, "mensaje", new Date(), false, "admin@correo.com");
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(noti));
        when(notificacionRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Notificacion actualizado = service.marcarComoLeida(1L);

        assertThat(actualizado.getLeido()).isTrue();
    }
}
