package com.Microservicios.GestionUsuarios.repository;

import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRolRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    private Rol rolGuardado;

    @BeforeEach
    void setUp() {
        // Crear el rol ADMIN si no existe
        rolGuardado = rolRepository.findByNombre("ADMIN").orElseGet(() -> {
            Rol nuevoRol = new Rol();
            nuevoRol.setNombre("ADMIN");
            return rolRepository.save(nuevoRol);
        });
    }

    @Test
    void testGuardarUsuarioConRol() {
        Usuario usuario = Usuario.builder()
                .nombre("Nicolás")
                .apellido("Lazo")
                .correo("niko@correo.com")
                .clave("secreta")
                .estado(true)
                .telefono("123456789")
                .rol(rolGuardado)
                .build();

        Usuario guardado = usuarioRepository.save(usuario);

        assertNotNull(guardado.getIdUsuario(), "El usuario debe tener un ID");
        assertEquals("ADMIN", guardado.getRol().getNombre(), "El rol asignado debe ser ADMIN");
        assertTrue(usuarioRepository.existsByCorreo("niko@correo.com"), "El usuario debe existir por correo");
    }

    @Test
    void testBuscarRolPorNombre() {
        Optional<Rol> admin = rolRepository.findByNombre("ADMIN");
        assertTrue(admin.isPresent(), "Debe encontrarse el rol ADMIN");
        assertEquals("ADMIN", admin.get().getNombre());
    }
}
