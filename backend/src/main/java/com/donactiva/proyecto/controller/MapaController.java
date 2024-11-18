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
            new PuntoMapa("CUT", 20.566867581314288, -103.22858964553858), //CUT
            new PuntoMapa("CUCEI", 20.6550760553232, -103.32549080732102), //CUCEI
            new PuntoMapa("CUCEA", 20.740948684177095, -103.38092100320853), //CUCEA
            new PuntoMapa("CUCSH", 20.74172881630103, -103.37639383204498), //CUCSH
            new PuntoMapa("CUGDL", 20.6943551031588, -103.34976022226601), // CUGDL
            new PuntoMapa("CUCBA", 20.746827343425192, -103.51034773996949), //CUCBA
            new PuntoMapa("CUTlaquepaque", 20.602974705807025, -103.35312618972037), //CUTlaquepaque
            new PuntoMapa("CUTlajo", 20.464746615427355, -103.41312646324454), //CUTlajo
            new PuntoMapa("CUCS", 20.687407679934427, -103.3334640801651) //CUCS
        );
    }

    @GetMapping("/google-maps-api-key")
    public String obtenerGoogleMapsApiKey() {
        return googleMapsService.getApiKey();
    }
}

