package com.donactiva.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donactiva.proyecto.model.Localizacion;
import com.donactiva.proyecto.service.LocalizacionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class LocalizacionController {

    @Autowired
    private LocalizacionService localizacionService;

    @GetMapping
    public ResponseEntity<Iterable<Localizacion>> obtenerLocalizaciones() {
        Iterable<Localizacion> localizaciones = localizacionService.obtenerTodasLocalizaciones();
        return ResponseEntity.ok(localizaciones);
    }

    @PostMapping("/guardarLocalizacion")
    public ResponseEntity<Localizacion> guardarLocalizacion(@RequestBody Localizacion localizacion) {
        localizacionService.guardarLocalizacion(localizacion);
        return ResponseEntity.ok(localizacion);
        
    }
    

}
