package com.donactiva.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.donactiva.proyecto.model.Localizacion;
import com.donactiva.proyecto.service.LocalizacionService;

@RestController
@RequestMapping
public class LocalizacionController {

    @Autowired
    private LocalizacionService localizacionService;

    @GetMapping
    public ResponseEntity<List<Localizacion>> obtenerLocalizaciones() {
        List<Localizacion> localizaciones = localizacionService.obtenerTodasLocalizaciones();
        return ResponseEntity.ok(localizaciones);
    }

}
