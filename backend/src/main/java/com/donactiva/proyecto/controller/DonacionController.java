package com.donactiva.proyecto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.donactiva.proyecto.service.DonacionService;
import com.donactiva.proyecto.model.Donacion;


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
      
}
