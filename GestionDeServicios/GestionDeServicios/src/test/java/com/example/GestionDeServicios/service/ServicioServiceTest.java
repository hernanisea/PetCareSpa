package com.example.GestionDeServicios.service;

import com.example.GestionDeServicios.dto.ServicioRequest;
import com.example.GestionDeServicios.model.Servicio;
import com.example.GestionDeServicios.model.TipoServicio;
import com.example.GestionDeServicios.repository.ServicioRepository;
import com.example.GestionDeServicios.repository.TipoServicioRepository;
import com.example.GestionDeServicios.services.ServicioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServicioServiceTest {

    @Mock
    private ServicioRepository servicioRepository;

    @Mock
    private TipoServicioRepository tipoServicioRepository;

    @InjectMocks
    private ServicioService servicioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveDebeCrearUnServicio() {
        ServicioRequest request = new ServicioRequest();
        request.setNombre("Vacunación");
        request.setDescripcion("Vacuna antirrábica");
        request.setPrecio(15000);
        request.setTipoServicioId(1L);

        TipoServicio tipo = new TipoServicio();
        tipo.setIdTipo(1L);
        tipo.setNombre("Vacunas");

        when(tipoServicioRepository.findById(1L)).thenReturn(Optional.of(tipo));
        when(servicioRepository.save(any(Servicio.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Servicio resultado = servicioService.save(request);

        assertThat(resultado.getNombre()).isEqualTo("Vacunación");
        assertThat(resultado.getTipoServicio().getNombre()).isEqualTo("Vacunas");
    }
}
