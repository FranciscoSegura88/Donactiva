package com.donactiva.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.donactiva.proyecto.service.GoogleMapsService;

@CrossOrigin(origins = "*")
@RestController
public class MapaController {

    @Autowired
    private GoogleMapsService googleMapsService;

    @GetMapping("/google-maps-api-key")
    public String obtenerGoogleMapsApiKey() {
        return googleMapsService.getApiKey();
    }
}

