package com.example.GestionDeServicios.service;

import com.example.GestionDeServicios.model.TipoServicio;
import com.example.GestionDeServicios.repository.TipoServicioRepository;
import com.example.GestionDeServicios.services.TipoServicioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TipoServicioServiceTest {

    @Mock
    private TipoServicioRepository tipoServicioRepository;

    @InjectMocks
    private TipoServicioService tipoServicioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveDebeGuardarTipoServicio() {
        TipoServicio tipo = new TipoServicio(1L, "Cirugía", null);
        when(tipoServicioRepository.save(any())).thenReturn(tipo);

        TipoServicio resultado = tipoServicioService.save("Cirugía");

        assertThat(resultado.getNombre()).isEqualTo("Cirugía");
    }

    @Test
    void findByIdDebeRetornarTipoServicio() {
        TipoServicio tipo = new TipoServicio(2L, "Vacunas", null);
        when(tipoServicioRepository.findById(2L)).thenReturn(Optional.of(tipo));

        TipoServicio resultado = tipoServicioService.findById(2L);

        assertThat(resultado.getNombre()).isEqualTo("Vacunas");
    }

    @Test
    void findByIdDebeLanzarExcepcionSiNoExiste() {
        when(tipoServicioRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> tipoServicioService.findById(999L));
    }

    @Test
    void deleteByIdDebeEliminar() {
        when(tipoServicioRepository.existsById(5L)).thenReturn(true);
        tipoServicioService.deleteById(5L);
        verify(tipoServicioRepository, times(1)).deleteById(5L);
    }

    @Test
    void deleteByIdDebeLanzarExcepcionSiNoExiste() {
        when(tipoServicioRepository.existsById(10L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> tipoServicioService.deleteById(10L));
    }

    @Test
    void findAllDebeRetornarLista() {
        List<TipoServicio> lista = List.of(new TipoServicio(1L, "Consulta", null));
        when(tipoServicioRepository.findAll()).thenReturn(lista);

        List<TipoServicio> resultado = tipoServicioService.findAll();

        assertThat(resultado).hasSize(1);
    }
}
