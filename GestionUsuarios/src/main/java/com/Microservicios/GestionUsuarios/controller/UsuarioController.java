package com.Microservicios.GestionUsuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> getRoles() {
        List<Rol> roles = rolRepository.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioRequest request) {
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException("Ya existe un usuario con el correo: " + request.getCorreo());
        }

        Rol rol = rolRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + request.getId()));

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreo(request.getCorreo());
        usuario.setClave(request.getClave());
        usuario.setEstado(request.getEstado());
        usuario.setTelefono(request.getTelefono());
        usuario.setIdDireccion(request.getIdDireccion());
        usuario.setIdMascota(request.getIdMascota());
        usuario.setIdComentario(request.getIdComentario());
        usuario.setIdNotificacion(request.getIdNotificacion());
      usuario.setIdHistorial(request.getIdHistorial());
        usuario.setRol(rol);

        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        Rol rol = rolRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + request.getId()));

        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreo(request.getCorreo());
        usuario.setClave(request.getClave());
        usuario.setEstado(request.getEstado());
        usuario.setTelefono(request.getTelefono());
        usuario.setIdDireccion(request.getIdDireccion());
        usuario.setIdMascota(request.getIdMascota());
        usuario.setIdComentario(request.getIdComentario());
        usuario.setIdNotificacion(request.getIdNotificacion());

        usuario.setIdHistorial(request.getIdHistorial());
        usuario.setRol(rol);

        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No existe un usuario con ID: " + id);
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // DTO para entrada de datos
    public static class UsuarioRequest {
        private String nombre;
        private String apellido;
        private String correo;
        private String clave;
        private Boolean estado;
        private String telefono;
        private Long idDireccion;
        private Long idMascota;
        private Long idComentario;
        private Long idNotificacion;
        private Long idReportes;
        private Long idHistorial;
        private Long id;

        // Getters y Setters

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getApellido() { return apellido; }
        public void setApellido(String apellido) { this.apellido = apellido; }

        public String getCorreo() { return correo; }
        public void setCorreo(String correo) { this.correo = correo; }

        public String getClave() { return clave; }
        public void setClave(String clave) { this.clave = clave; }

        public Boolean getEstado() { return estado; }
        public void setEstado(Boolean estado) { this.estado = estado; }

        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }

        public Long getIdDireccion() { return idDireccion; }
        public void setIdDireccion(Long idDireccion) { this.idDireccion = idDireccion; }

        public Long getIdMascota() { return idMascota; }
        public void setIdMascota(Long idMascota) { this.idMascota = idMascota; }

        public Long getIdComentario() { return idComentario; }
        public void setIdComentario(Long idComentario) { this.idComentario = idComentario; }

        public Long getIdNotificacion() { return idNotificacion; }
        public void setIdNotificacion(Long idNotificacion) { this.idNotificacion = idNotificacion; }

        public Long getIdReportes() { return idReportes; }
        public void setIdReportes(Long idReportes) { this.idReportes = idReportes; }

        public Long getIdHistorial() { return idHistorial; }
        public void setIdHistorial(Long idHistorial) { this.idHistorial = idHistorial; }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
    }
}
