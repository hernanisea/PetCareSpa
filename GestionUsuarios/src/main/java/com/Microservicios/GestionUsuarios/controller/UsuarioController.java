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

import com.Microservicios.GestionUsuarios.dto.RolConUsuariosResponse;
import com.Microservicios.GestionUsuarios.dto.UsuarioRequest;
import com.Microservicios.GestionUsuarios.dto.UsuarioResponse;
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
            @ApiResponse(responseCode = "200", description = "Lista de roles encontrada", content = @Content(schema = @Schema(implementation = RolConUsuariosResponse.class))),
            @ApiResponse(responseCode = "204", description = "No se encontraron roles")
    })
    @GetMapping("/roles")
    public ResponseEntity<List<RolConUsuariosResponse>> getRolesConUsuarios() {
        List<Rol> roles = rolRepository.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<RolConUsuariosResponse> response = roles.stream()
                .map(usuarioService::convertirRolConUsuarios)
                .toList();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios encontrada", content = @Content(schema = @Schema(implementation = UsuarioResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        List<UsuarioResponse> response = usuarios.stream()
                .map(usuarioService::convertirUsuarioResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        UsuarioResponse dto = usuarioService.convertirUsuarioResponse(usuario);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente", content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "409", description = "El correo ya está registrado"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioResponse> crearUsuario(@Valid @RequestBody UsuarioRequest request) {
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un usuario con el correo: " + request.getCorreo());
        }

        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreo(request.getCorreo());
        usuario.setClave(passwordEncoder.encode(request.getClave()));
        usuario.setEstado(request.getEstado());
        usuario.setTelefono(request.getTelefono());
        usuario.setRol(rol);
        usuario.setRelaciones(request.getRelaciones());

        Usuario creado = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.convertirUsuarioResponse(creado));

    }

    @Operation(summary = "Actualizar un usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente", content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario o rol no encontrado")
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioResponse> actualizarUsuario(@PathVariable Long id,
            @Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con ID: " + id));

        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado"));

        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreo(request.getCorreo());
        usuario.setClave(passwordEncoder.encode(request.getClave()));
        usuario.setEstado(request.getEstado());
        usuario.setTelefono(request.getTelefono());
        usuario.setRol(rol);
        usuario.setRelaciones(request.getRelaciones());

        Usuario actualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioService.convertirUsuarioResponse(actualizado));
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

    @GetMapping("/{id}/mascotas")
    @Operation(summary = "Obtener las mascotas de un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de mascotas del usuario obtenida exitosamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    public ResponseEntity<?> obtenerMascotasDelUsuario(@PathVariable Long id) {
        try {
            List<Map<String, Object>> mascotas = usuarioService.obtenerMascotasDeUsuario(id);
            return ResponseEntity.ok(mascotas);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }

    @Operation(summary = "Obtener las direcciones de un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Direcciones del usuario obtenidas exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}/direcciones")
    public ResponseEntity<List<Map<String, Object>>> obtenerDireccionesDelUsuario(@PathVariable Long id) {
        List<Map<String, Object>> direcciones = usuarioService.obtenerDireccionesDeUsuario(id);
        return ResponseEntity.ok(direcciones);
    }

    @Operation(summary = "Obtener los comentarios de un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentarios del usuario obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}/comentarios")
    public ResponseEntity<List<Map<String, Object>>> obtenerComentariosDelUsuario(@PathVariable Long id) {
        List<Map<String, Object>> comentarios = usuarioService.obtenerComentariosDeUsuario(id);
        return ResponseEntity.ok(comentarios);
    }

    @Operation(summary = "Obtener las notificaciones de un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificaciones del usuario obtenidas exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}/notificaciones")
    public ResponseEntity<List<Map<String, Object>>> obtenerNotificacionesDelUsuario(@PathVariable Long id) {
        List<Map<String, Object>> notificaciones = usuarioService.obtenerNotificacionesDeUsuario(id);
        return ResponseEntity.ok(notificaciones);
    }

}
