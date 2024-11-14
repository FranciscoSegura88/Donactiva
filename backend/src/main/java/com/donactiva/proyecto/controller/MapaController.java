package com.donactiva.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donactiva.proyecto.model.PuntoMapa;
import com.donactiva.proyecto.service.GoogleMapsService;

import java.util.Arrays;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class MapaController {

    @Autowired
    private GoogleMapsService googleMapsService;

    @GetMapping("/elegirPunto")
    public List<PuntoMapa> obtenerPuntos() {
        return Arrays.asList(
            new PuntoMapa(-34.6037, -58.3816),
            new PuntoMapa(-34.6097, -58.3811),
            new PuntoMapa(-34.6013, -58.3869)
        );
    }

    @GetMapping("/google-maps-api-key")
    public String obtenerGoogleMapsApiKey() {
        return googleMapsService.getApiKey();
    }
}

