package com.Microservicios.HistorialClinico.repository;

import com.Microservicios.HistorialClinico.model.HistorialClinico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HistorialClinicoRepositoryTest {

    @Autowired
    private HistorialClinicoRepository historialRepo;

    @Test
    public void testGuardarYBuscarHistorial() {
        HistorialClinico nuevo = new HistorialClinico();
        nuevo.setFecha(new Date());
        nuevo.setDiagnostico("Prueba de diagnóstico");
        nuevo.setUsuarioId(1L);
        nuevo.setMascotaId(2L);

        HistorialClinico guardado = historialRepo.save(nuevo);

        assertThat(guardado.getIdHistorial()).isNotNull();
        assertThat(guardado.getDiagnostico()).isEqualTo("Prueba de diagnóstico");
    }

    @Test
    public void testListarHistoriales() {
        List<HistorialClinico> historiales = historialRepo.findAll();
        assertThat(historiales).isNotNull();
    }
}
