package com.donactiva.proyecto.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.donactiva.proyecto.model.Canjes;
import com.donactiva.proyecto.service.CanjesService;

@RestController
@RequestMapping("/canjes")
public class CanjesController {

    @Autowired
    private CanjesService canjesService;

     @PostMapping("/canjearPuntos")
     public Canjes canjearPuntos(@RequestBody Canjes canje,
                                @RequestParam int idUsuario,
                                @RequestParam int cantidadPuntos,
                                @RequestParam String convenio) {
        
         return canjesService.canjearPuntos(canje, idUsuario, cantidadPuntos, convenio);
     }
     


}
