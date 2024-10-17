package com.donactiva.proyecto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import com.donactiva.proyecto.service.DonacionService;

import jakarta.persistence.EntityNotFoundException;

import com.donactiva.proyecto.model.Donacion;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class DonacionController {

    @Autowired
    private DonacionService donacionService;

    @PostMapping("/confirmarDonacion")
    public ResponseEntity<Donacion> crearDonacion(@RequestBody Donacion donacion,
                                                    @RequestParam int idArticulo,
                                                    @RequestParam int idLocalizacion,
                                                    @RequestParam int idUsuario) {
        Donacion nuevaDonacion = donacionService.guardarDonacion(donacion, idArticulo, idLocalizacion, idUsuario);

    
          
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDonacion);
      }

      @GetMapping("/misDonaciones")
      public ResponseEntity<Iterable<Donacion>> obtenerDonaciones(@RequestParam int idUsuario) {
        Iterable<Donacion> donacion = donacionService.obtenerTodasDonaciones(idUsuario);
        return ResponseEntity.ok(donacion);
    }

    @PutMapping("/recolectada/{id}")
    public ResponseEntity<Donacion> marcarComoRecolectadaEntity(@PathVariable int id) {
    try {
        Donacion donacionOpt = donacionService.marcarComoRecolectada(id);
        return ResponseEntity.ok(donacionOpt);
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
      
      
}
