package com.donactiva.proyecto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.donactiva.proyecto.model.Articulos;
import com.donactiva.proyecto.service.ArticulosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class ArticulosController {

    @Autowired
    private ArticulosService articulosService;

    @GetMapping("/getArticulos")
    public Optional<Articulos> obtenerArticulosPorId(@RequestParam int id) {
        return articulosService.obtenerArticulosPorId(id);
    }

    @PostMapping("/guardarArticulos")
    public ResponseEntity<Articulos> guardarArticulos(@RequestBody Articulos articulos) {
        Articulos nuevoArticulo = articulosService.guardarArticulos(articulos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoArticulo);
    }
}
