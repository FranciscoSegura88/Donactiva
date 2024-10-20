package com.donactiva.proyecto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityNotFoundException;

import com.donactiva.proyecto.service.DonacionService;
import com.donactiva.proyecto.model.Donacion;
import com.donactiva.proyecto.model.Donacion.EstadoDonacion;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DonacionController {

    @Autowired
    private DonacionService donacionService;

    @GetMapping("/misDonaciones")
    public ResponseEntity<Iterable<Donacion>> obtenerDonaciones(
            @RequestParam int idUsuario,
            @RequestParam EstadoDonacion estadoDonacion) {

        Iterable<Donacion> donacion;
        donacion = donacionService.obtenerDonacionesPorEstado(idUsuario, estadoDonacion);

        return ResponseEntity.ok(donacion);
    }

    @PostMapping("/confirmarDonacion")
    public ResponseEntity<Donacion> crearDonacion(@RequestBody Donacion donacion,
            @RequestParam int idArticulo,
            @RequestParam int idLocalizacion,
            @RequestParam int idUsuario) {
        
            Donacion nuevaDonacion = donacionService.guardarDonacion(donacion, idArticulo, idLocalizacion, idUsuario);

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
