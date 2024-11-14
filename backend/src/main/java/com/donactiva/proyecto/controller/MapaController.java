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
            new PuntoMapa(20.66023887180995, -103.3475682553707),
            new PuntoMapa(20.696808745450365, -103.37588044587726),
            new PuntoMapa(20.67611660516869, -103.38690720936174),
            new PuntoMapa(20.645684156767654, -103.31260262484513)
        );
    }

    @GetMapping("/google-maps-api-key")
    public String obtenerGoogleMapsApiKey() {
        return googleMapsService.getApiKey();
    }
}

