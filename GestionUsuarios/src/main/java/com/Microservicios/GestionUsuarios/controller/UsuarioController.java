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
import com.Microservicios.GestionUsuarios.service.RolService;
import com.Microservicios.GestionUsuarios.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private RolService rolService;
    
    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> getRoles() {
    List<Rol> roles = rolService.obtenerRoles();
    if (roles.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(roles);
    }

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioRequest request) {
        Usuario nuevo = usuarioService.crearUsuario(
                request.getNombre(),
                request.getEmail(),
                request.getPassword(),
                request.getRolId()
        );
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        Usuario actualizado = usuarioService.actualizarUsuario(
                id,
                request.getNombre(),
                request.getEmail(),
                request.getPassword(),
                request.getRolId()
        );
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // DTO para entrada de datos
    public static class UsuarioRequest {
        private String nombre;
        private String email;
        private String password;
        private Long rolId;

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public Long getRolId() { return rolId; }
        public void setRolId(Long rolId) { this.rolId = rolId; }
    }
}
