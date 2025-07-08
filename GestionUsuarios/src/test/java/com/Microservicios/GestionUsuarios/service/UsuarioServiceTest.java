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

import com.Microservicios.GestionUsuarios.client.ComentarioClient;
import com.Microservicios.GestionUsuarios.client.DireccionClient;
import com.Microservicios.GestionUsuarios.client.HistorialClinicoClient;
import com.Microservicios.GestionUsuarios.client.MascotaClient;
import com.Microservicios.GestionUsuarios.client.NotificacionClient;
import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;

public class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private PasswordEncoder passwordEncoder;

    // Mock de los nuevos clientes necesarios para el constructor actualizado
    private MascotaClient mascotaClient;
    private DireccionClient direccionClient;
    private ComentarioClient comentarioClient;
    private NotificacionClient notificacionClient;
    private HistorialClinicoClient historialClinicoClient;

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        rolRepository = mock(RolRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);

        mascotaClient = mock(MascotaClient.class);
        direccionClient = mock(DireccionClient.class);
        comentarioClient = mock(ComentarioClient.class);
        notificacionClient = mock(NotificacionClient.class);
        historialClinicoClient = mock(HistorialClinicoClient.class);

        usuarioService = new UsuarioService(
                usuarioRepository,
                rolRepository,
                passwordEncoder,
                mascotaClient,
                direccionClient,
                comentarioClient,
                notificacionClient,
                historialClinicoClient
        );
    }

    @Test
    void crearUsuarioBasico_deberiaGuardarUsuario() {
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setNombre("Cliente");

        when(passwordEncoder.encode(anyString())).thenReturn("hashed-password");

        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario u = invocation.getArgument(0);
            u.setRol(rol); // aseguramos que el rol esté bien seteado
            return u;
        });

        Usuario usuario = usuarioService.crearUsuarioBasico("Nico", "Lazo", "nico@mail.com", "1234", "12345678", rol);

        assertEquals("Nico", usuario.getNombre());
        assertEquals("Lazo", usuario.getApellido());
        assertEquals("hashed-password", usuario.getClave());
        assertEquals("nico@mail.com", usuario.getCorreo());
        assertEquals("12345678", usuario.getTelefono());
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
