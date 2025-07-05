package com.Microservicios.GestionUsuarios.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.Microservicios.GestionUsuarios.dto.RolConUsuariosResponse;
import com.Microservicios.GestionUsuarios.dto.UsuarioResponse;
import com.Microservicios.GestionUsuarios.dto.UsuarioResponseRol;
import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final WebClient direccionWebClient;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository,
                          PasswordEncoder passwordEncoder,
                          WebClient.Builder webClientBuilder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.direccionWebClient = webClientBuilder.baseUrl("http://localhost:8081/api/v1/direcciones").build();
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
    }

    public Map<String, Object> obtenerUsuarioConDireccion(Long idUsuario) {
        Usuario usuario = obtenerUsuarioPorId(idUsuario);
        Map<String, Object> direccion = this.direccionWebClient.get()
                .uri("/" + usuario.getIdDireccion())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        return Map.of(
                "usuario", usuario,
                "direccion", direccion
        );
    }

    @Transactional
    public Usuario crearUsuario(String nombre, String apellido, String correo, String clave, boolean estado, String telefono, long id) {

        if (usuarioRepository.existsByCorreo(correo)) {
            throw new RuntimeException("Ya existe un usuario con el correo: " + correo);
        }

        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setClave(passwordEncoder.encode(clave));
        usuario.setEstado(estado);
        usuario.setTelefono(telefono);
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario actualizarUsuario(Long idUsuario, String nombre, String apellido, String correo, String clave,
                                     Boolean estado, String telefono, Long id) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setClave(passwordEncoder.encode(clave));
        usuario.setEstado(estado);
        usuario.setTelefono(telefono);
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

    
    public Usuario crearUsuarioBasico(String nombre, String apellido, String correo, String clave, String telefono, Rol coordinadorClinica) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setClave(passwordEncoder.encode(clave));
        usuario.setEstado(true);
        usuario.setTelefono(telefono);
        usuario.setRol(coordinadorClinica);

        return usuarioRepository.save(usuario);
    }
    public UsuarioResponse convertirUsuarioResponse(Usuario usuario) {
    UsuarioResponse dto = new UsuarioResponse();
    dto.setId(usuario.getIdUsuario());
    dto.setNombre(usuario.getNombre());
    dto.setApellido(usuario.getApellido());
    dto.setCorreo(usuario.getCorreo());
    dto.setTelefono(usuario.getTelefono());
    dto.setEstado(usuario.getEstado());
    dto.setRol(usuario.getRol().getNombre());
    return dto;
    }

    public UsuarioResponseRol convertirUsuarioResponseRol(Usuario usuario) {
    UsuarioResponseRol dto1 = new UsuarioResponseRol();
    dto1.setId(usuario.getIdUsuario());
    dto1.setNombre(usuario.getNombre());
    dto1.setApellido(usuario.getApellido());
    dto1.setCorreo(usuario.getCorreo());
    dto1.setTelefono(usuario.getTelefono());
    dto1.setEstado(usuario.getEstado());
    return dto1;
    }


    public RolConUsuariosResponse convertirRolConUsuarios(Rol rol) {
    RolConUsuariosResponse dto1 = new RolConUsuariosResponse();
    dto1.setId(rol.getId());
    dto1.setNombre(rol.getNombre());
    
    List<UsuarioResponseRol> usuarios = rol.getUsuarios().stream()
            .map(this::convertirUsuarioResponseRol)
            .toList();

    dto1.setUsuarios(usuarios);
    return dto1;
    }

}
