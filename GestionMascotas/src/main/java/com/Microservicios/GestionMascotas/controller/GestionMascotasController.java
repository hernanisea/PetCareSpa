package com.Microservicios.GestionMascotas.controller;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Mascotas>> listarMascotas() {
        return ResponseEntity.ok(mascotasService.obtenerMascotas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascotas> obtenerMascota(@PathVariable Long id) {
        return ResponseEntity.ok(mascotasService.obtenerMascotaPorId(id));
    }

    @PostMapping
    public ResponseEntity<Mascotas> crearMascota(@RequestBody MascotaRequest request) {
        Mascotas nueva = mascotasService.crearMascota(
                request.getIdUsuario(),
                request.getNombre(),
                request.getEdad(),
                request.getSexo(),
                request.getPesoKg(),
                request.getFechaRegistro(),
                request.getIdEspecie(),
                request.getIdRaza()
        );
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascotas> actualizarMascota(@PathVariable Long id, @RequestBody MascotaRequest request) {
        Mascotas actualizada = mascotasService.actualizarMascota(
                id,
                request.getIdUsuario(),
                request.getNombre(),
                request.getEdad(),
                request.getSexo(),
                request.getPesoKg(),
                request.getFechaRegistro(),
                request.getIdEspecie(),
                request.getIdRaza()
        );
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotasService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
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
