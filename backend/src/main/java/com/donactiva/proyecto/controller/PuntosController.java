package com.donactiva.proyecto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.donactiva.proyecto.service.PuntosService;
import com.donactiva.proyecto.model.Puntos.EstadoPuntos;
import com.donactiva.proyecto.model.Puntos;

@RestController
@RequestMapping("/puntos")
public class PuntosController {

    @Autowired
    private PuntosService puntosService;

    @GetMapping("/misPuntos")
    public ResponseEntity<Iterable<Puntos>> obtenerPuntos(
        @RequestParam int idUsuario,
        @RequestParam(required = false) EstadoPuntos estadoPuntos) {
        Iterable<Puntos> puntos;
        if(estadoPuntos != null){
            puntos = puntosService.obtenerPuntosPorEstado(idUsuario, estadoPuntos);
        }else{
            puntos = puntosService.obtenerTodosPuntos(idUsuario);
        }
        return ResponseEntity.ok(puntos);
    }
}
