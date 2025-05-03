package com.example.GestionPersonal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.GestionPersonal.model.Personal;
import com.example.GestionPersonal.service.PersonalService;

@RestController
@RequestMapping("/api/v1/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping
    public ResponseEntity<List<Personal>> listarPersonal() {
        List<Personal> lista = personalService.listaPersonal();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Personal> guardarPersonal(@RequestBody Personal personal) {
        Personal nuevo = personalService.agregarPersonal(personal);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<Personal> actualizarPersonal(@PathVariable Long id, @RequestBody Personal personal) {
        Personal actualizado = personalService.actualizarPersonal(id, personal);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
}
