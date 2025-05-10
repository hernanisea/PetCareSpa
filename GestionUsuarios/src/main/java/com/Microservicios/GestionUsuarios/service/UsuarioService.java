package com.Microservicios.GestionUsuarios.service;

import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario crearUsuario(String nombre, String apellido, String correo, String clave,
                                Boolean estado, String telefono, Long idDireccion, Long idRol) {

        if (usuarioRepository.existsByCorreo(correo)) {
            throw new RuntimeException("Ya existe un usuario con el correo: " + correo);
        }

        Rol rol = rolRepository.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + idRol));

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setClave(clave);
        usuario.setEstado(estado);
        usuario.setTelefono(telefono);
        usuario.setIdDireccion(idDireccion);
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
    }

    @Transactional
    public Usuario actualizarUsuario(Long idUsuario, String nombre, String apellido, String correo, String clave,
                                     Boolean estado, String telefono, Long idDireccion, Long idRol) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        Rol rol = rolRepository.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + idRol));

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setClave(clave);
        usuario.setEstado(estado);
        usuario.setTelefono(telefono);
        usuario.setIdDireccion(idDireccion);
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminarUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RuntimeException("No existe un usuario con ID: " + idUsuario);
        }
        usuarioRepository.deleteById(idUsuario);
    }
}
