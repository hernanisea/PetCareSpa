package com.Microservicios.GestionUsuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuario(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario agregarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario actualizarUsuario(long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setNombreCompleto(usuarioActualizado.getNombreCompleto());
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
            usuarioExistente.setPasswordHash(usuarioActualizado.getPasswordHash());
            usuarioExistente.setRol(usuarioActualizado.getRol());
            usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }
}
