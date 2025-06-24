package com.example.Notificaciones.repository;

import com.example.Notificaciones.model.Notificacion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NotificacionRepositoryTest {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Test
    void testGuardarNotificacion() {
        Notificacion noti = new Notificacion(null, 1L, "Mensaje de prueba", new Date(), false, "admin@correo.com");
        Notificacion guardada = notificacionRepository.save(noti);

        assertThat(guardada.getIdNotificacion()).isNotNull();
        assertThat(guardada.getMensaje()).isEqualTo("Mensaje de prueba");
    }

    @Test
    void testBuscarPorUsuarioId() {
        Notificacion noti = new Notificacion(null, 2L, "Noti para usuario 2", new Date(), false, "admin@correo.com");
        notificacionRepository.save(noti);

        List<Notificacion> lista = notificacionRepository.findByUsuarioId(2L);
        assertThat(lista).isNotEmpty();
    }

    @Test
    void testBuscarPorCreador() {
        Notificacion noti = new Notificacion(null, 3L, "Mensaje por admin", new Date(), false, "admin@correo.com");
        notificacionRepository.save(noti);

        List<Notificacion> lista = notificacionRepository.findByCreadoPor("admin@correo.com");
        assertThat(lista).isNotEmpty();
    }
}
