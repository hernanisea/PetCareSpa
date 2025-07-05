package com.Microservicios.GestionUsuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;

class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private PasswordEncoder passwordEncoder;
    private WebClient.Builder webClientBuilder;
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        rolRepository = mock(RolRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        webClientBuilder = mock(WebClient.Builder.class);
        WebClient webClient = mock(WebClient.class);
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);

        usuarioService = new UsuarioService(usuarioRepository, rolRepository, passwordEncoder, webClientBuilder);
    }

    @Test
    void crearUsuarioBasico_deberiaGuardarUsuario() {
        Rol rol = new Rol();
        when(passwordEncoder.encode(anyString())).thenReturn("hashed");
        when(usuarioRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Usuario usuario = usuarioService.crearUsuarioBasico("Nico", "Lazo", "nico@mail.com", "1234", "12345678", rol);

        assertEquals("Nico", usuario.getNombre());
        assertEquals("hashed", usuario.getClave());
        assertEquals(rol, usuario.getRol());
    }

    @Test
    void crearUsuario_lanzaExcepcionSiCorreoExiste() {
        when(usuarioRepository.existsByCorreo("yaexiste@mail.com")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.crearUsuario("N", "L", "yaexiste@mail.com", "123", true, "123", 1L);
        });

        assertTrue(exception.getMessage().contains("Ya existe un usuario con el correo"));
    }
}
