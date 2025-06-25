package com.Microservicios.GestionUsuarios.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.Microservicios.GestionUsuarios.model.Rol;
import com.Microservicios.GestionUsuarios.model.Usuario;
import com.Microservicios.GestionUsuarios.repository.RolRepository;
import com.Microservicios.GestionUsuarios.repository.UsuarioRepository;
import com.Microservicios.GestionUsuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtener todos los roles disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de roles encontrada", 
            content = @Content(schema = @Schema(implementation = Rol.class))),
        @ApiResponse(responseCode = "204", description = "No se encontraron roles")
    })
    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> getRoles() {
        List<Rol> roles = rolRepository.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    @Operation(summary = "Listar todos los usuarios")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios encontrada", 
            content = @Content(schema = @Schema(implementation = Usuario.class)))
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @Operation(summary = "Obtener un usuario por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado", 
            content = @Content(schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con ID: " + id));
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Obtener un usuario con su información de dirección")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario con dirección encontrado")
    })
    @GetMapping("/{id}/con-direccion")
    public ResponseEntity<Map<String, Object>> obtenerUsuarioConDireccion(@PathVariable Long id) {
        Map<String, Object> usuarioConDireccion = usuarioService.obtenerUsuarioConDireccion(id);
        return ResponseEntity.ok(usuarioConDireccion);
    }

    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente", 
            content = @Content(schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "409", description = "El correo ya está registrado"),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioRequest request) {
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un usuario con el correo: " + request.getCorreo());
        }

        Rol rol = rolRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreo(request.getCorreo());
        usuario.setClave(passwordEncoder.encode(request.getClave()));
        usuario.setEstado(request.getEstado());
        usuario.setTelefono(request.getTelefono());
        usuario.setIdDireccion(request.getIdDireccion());
        usuario.setIdMascota(request.getIdMascota());
        usuario.setIdComentario(request.getIdComentario());
        usuario.setIdNotificacion(request.getIdNotificacion());
        usuario.setIdHistorial(request.getIdHistorial());
        usuario.setRol(rol);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    @Operation(summary = "Actualizar un usuario existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente", 
            content = @Content(schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario o rol no encontrado")
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con ID: " + id));

        Rol rol = rolRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado"));

        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreo(request.getCorreo());
        usuario.setClave(passwordEncoder.encode(request.getClave()));
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

    @Operation(summary = "Eliminar un usuario por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un usuario con ID: " + id);
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
