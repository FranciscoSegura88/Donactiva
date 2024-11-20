package com.donactiva.proyecto.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.donactiva.proyecto.model.Localizacion;
import com.donactiva.proyecto.service.LocalizacionService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LocalizacionController {

    @Autowired
    private LocalizacionService localizacionService;

    @GetMapping("/obtenerLocalizaciones")
    public ResponseEntity<Iterable<Localizacion>> obtenerLocalizaciones() {

        Iterable<Localizacion> localizaciones = localizacionService.obtenerTodasLocalizaciones();
        return ResponseEntity.ok(localizaciones);
    }

    @PostMapping("/guardarLocalizacion")
    public ResponseEntity<Localizacion> guardarLocalizacion(@RequestBody Localizacion localizacion) {

        localizacionService.guardarLocalizacion(localizacion);
        return ResponseEntity.ok(localizacion);

    }

    @PutMapping("/modificarLocalizacion/{idLocalizacion}")
    public ResponseEntity<?> modificarLocalizacion(
            @PathVariable int idLocalizacion,
            @RequestBody Localizacion localizacion) {
        try {
            Localizacion localizacionModificada = localizacionService.modificarLocalizacion(idLocalizacion,
                    localizacion);
            return ResponseEntity.ok(localizacionModificada);
        } catch (EntityNotFoundException e) {
            Map<String, String> mensaje = new HashMap<>();
            mensaje.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }

}
