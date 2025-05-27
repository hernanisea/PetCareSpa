package com.Microservicios.GestionMascotas.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Microservicios.GestionMascotas.model.Especie;
import com.Microservicios.GestionMascotas.model.Mascotas;
import com.Microservicios.GestionMascotas.model.Raza;
import com.Microservicios.GestionMascotas.service.EspecieService;
import com.Microservicios.GestionMascotas.service.MascotasService;
import com.Microservicios.GestionMascotas.service.RazaService;

@RestController
@RequestMapping("/api/v1/mascotas")
@CrossOrigin(origins = "*")
public class GestionMascotasController {

    @Autowired
    private EspecieService especieService;

    @Autowired
    private RazaService razaService;

    @Autowired
    private MascotasService mascotasService;

    // --- Controlador de Especie ---

    @GetMapping("/especies")
    public ResponseEntity<List<Especie>> listarEspecies() {
        List<Especie> especies = especieService.obtenerTodas();
        if (especies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(especies);
    }

    @GetMapping("/especies/{id}")
    public ResponseEntity<Especie> obtenerEspecie(@PathVariable Long id) {
        return ResponseEntity.ok(especieService.obtenerPorId(id));
    }

    @PostMapping("/especies")
    public ResponseEntity<Especie> crearEspecie(@RequestBody Especie especie) {
        return ResponseEntity.ok(especieService.guardar(especie));
    }

    @DeleteMapping("/especies/{id}")
    public ResponseEntity<Void> eliminarEspecie(@PathVariable Long id) {
        especieService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // --- Controlador de Raza ---

    @GetMapping("/razas")
    public ResponseEntity<List<Raza>> listarRazas() {
        List<Raza> razas = razaService.obtenerTodas();
        if (razas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(razas);
    }

    @GetMapping("/razas/{id}")
    public ResponseEntity<Raza> obtenerRaza(@PathVariable Long id) {
        return ResponseEntity.ok(razaService.obtenerPorId(id));
    }

    @PostMapping("/razas")
    public ResponseEntity<Raza> crearRaza(@RequestBody Raza raza) {
        return ResponseEntity.ok(razaService.guardar(raza));
    }

    @DeleteMapping("/razas/{id}")
    public ResponseEntity<Void> eliminarRaza(@PathVariable Long id) {
        razaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // --- Controlador de Mascotas ---

    @GetMapping
    public List<Mascotas> obtenerMascotas() {
        return mascotasService.obtenerMascotas();
    }

    // Obtener mascota por ID
    @GetMapping("/{id}")
    public Mascotas obtenerMascotaPorId(@PathVariable Long id) {
        return mascotasService.obtenerMascotaPorId(id);
    }

    // Crear una nueva mascota
    @PostMapping
    public Mascotas crearMascota(
            @RequestParam Integer idUsuario,
            @RequestParam String nombre,
            @RequestParam Integer edad,
            @RequestParam String sexo,
            @RequestParam Integer pesoKg,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaRegistro,
            @RequestParam Long idEspecie,
            @RequestParam Long idRaza,
            @RequestParam(required = false) Long idReserva
    ) {
        return mascotasService.crearMascota(idUsuario, nombre, edad, sexo, pesoKg, fechaRegistro, idEspecie, idRaza, idReserva);
    }

    // Actualizar mascota existente
    @PutMapping("/{id}")
    public Mascotas actualizarMascota(
            @PathVariable Long id,
            @RequestParam Integer idUsuario,
            @RequestParam String nombre,
            @RequestParam Integer edad,
            @RequestParam String sexo,
            @RequestParam Integer pesoKg,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaRegistro,
            @RequestParam Long idEspecie,
            @RequestParam Long idRaza,
            @RequestParam(required = false) Long idReserva
    ) {
        return mascotasService.actualizarMascota(id, idUsuario, nombre, edad, sexo, pesoKg, fechaRegistro, idEspecie, idRaza, idReserva);
    }

    // Eliminar mascota
    @DeleteMapping("/{id}")
    public void eliminarMascota(@PathVariable Long id) {
        mascotasService.eliminarMascota(id);
    }

    // --- DTO interno para Mascota ---
    public static class MascotaRequest {
        private Integer idUsuario;
        private String nombre;
        private Integer edad;
        private String sexo;
        private Integer pesoKg;
        private Date fechaRegistro;
        private Long idEspecie;
        private Long idRaza;

        public Integer getIdUsuario() { return idUsuario; }
        public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public Integer getEdad() { return edad; }
        public void setEdad(Integer edad) { this.edad = edad; }

        public String getSexo() { return sexo; }
        public void setSexo(String sexo) { this.sexo = sexo; }

        public Integer getPesoKg() { return pesoKg; }
        public void setPesoKg(Integer pesoKg) { this.pesoKg = pesoKg; }

        public Date getFechaRegistro() { return fechaRegistro; }
        public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

        public Long getIdEspecie() { return idEspecie; }
        public void setIdEspecie(Long idEspecie) { this.idEspecie = idEspecie; }

        public Long getIdRaza() { return idRaza; }
        public void setIdRaza(Long idRaza) { this.idRaza = idRaza; }
    }
}
