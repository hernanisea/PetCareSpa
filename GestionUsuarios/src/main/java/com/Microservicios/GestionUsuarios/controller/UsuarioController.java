package com.Microservicios.GestionUsuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> getRoles() {
        List<Rol> roles = rolService.obtenerTodos();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
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
                request.getApellido(),
                request.getCorreo(),
                request.getClave(),
                request.getEstado(),
                request.getTelefono(),
                request.getIdDireccion(),
                request.getId()
        );
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        Usuario actualizado = usuarioService.actualizarUsuario(
                id,
                request.getNombre(),
                request.getApellido(),
                request.getCorreo(),
                request.getClave(),
                request.getEstado(),
                request.getTelefono(),
                request.getIdDireccion(),
                request.getId()
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
        private String apellido;
        private String correo;
        private String clave;
        private Boolean estado;
        private String telefono;
        private Long idDireccion;
        private Long Id;

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

        public Long getId() { return Id; }
        public void setId(Long Id) { this.Id = Id; }
    }
}
