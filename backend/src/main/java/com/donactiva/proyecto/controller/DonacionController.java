package com.donactiva.proyecto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityNotFoundException;

import com.donactiva.proyecto.service.DonacionService;
import com.donactiva.proyecto.model.Donacion;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DonacionController {

    @Autowired
    private DonacionService donacionService;

    @GetMapping("/misDonaciones")
    public ResponseEntity<?> obtenerDonaciones(
            @RequestParam int idUsuario) {

        Iterable<Donacion> donaciones = donacionService.obtenerDonaciones(idUsuario);

        if (!donaciones.iterator().hasNext()) {
            Map<String, String> mensaje = new HashMap<>();
            mensaje.put("Ups!", "No hay donaciones todavia");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        return ResponseEntity.ok(donaciones);
    }

    @GetMapping("/consultarDonacion")
    public Optional<Donacion> consultarDonacion(@PathVariable int idDonacion) {


        return donacionService.consultarDonacion(idDonacion);
    }
    

    @PostMapping("/confirmarDonacion")
    public ResponseEntity<Donacion> crearDonacion(
            @RequestParam int idArticulo,
            @RequestParam int idLocalizacion,
            @RequestParam int idUsuario) {

        Donacion nuevaDonacion = donacionService.guardarDonacion(idArticulo, idLocalizacion, idUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDonacion);
    }

    @PutMapping("/recolectada/{idDonacion}")
    public ResponseEntity<Donacion> marcarComoRecolectadaEntity(@PathVariable int idDonacion) {
        try {
            Donacion donacion = donacionService.marcarComoRecolectada(idDonacion);
            return ResponseEntity.ok(donacion);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}