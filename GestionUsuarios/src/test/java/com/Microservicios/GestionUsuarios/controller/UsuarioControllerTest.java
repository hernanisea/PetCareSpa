package com.Microservicios.GestionUsuarios.controller;

import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // <- revierte los datos insertados en cada test
public class UsuarioControllerTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        usuarioRepository.deleteAll();
        rolRepository.deleteAll();

        Rol rol = new Rol();
        rol.setNombre("ADMIN");
        Rol rolGuardado = rolRepository.save(rol);

        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setCorreo("juan@correo.com");
        usuario.setClave("1234");
        usuario.setEstado(true);
        usuario.setTelefono("123456789");
        usuario.setRol(rolGuardado);

        usuarioRepository.save(usuario);
    }

    @Test
    void testBuscarUsuarioPorCorreo() {
        Usuario usuario = usuarioRepository.findByCorreo("juan@correo.com");
        assertThat(usuario).isNotNull();
        assertThat(usuario.getNombre()).isEqualTo("Juan");
    }
}
