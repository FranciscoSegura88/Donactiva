package com.donactiva.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.donactiva.proyecto.model.Articulos;
import com.donactiva.proyecto.repository.ArticulosRepository;

interface ArticulosServiceInterface {
    Articulos guardarArticulos(Articulos articulos);

    Optional<Articulos> obtenerArticulosPorId(int id);
}

@Service
public class ArticulosService implements ArticulosServiceInterface {

    @Autowired
    private ArticulosRepository articulosRepository;

    @Override
    public Articulos guardarArticulos(Articulos articulos) {
        return articulosRepository.save(articulos);
    }

    @Override
    public Optional<Articulos> obtenerArticulosPorId(int id) {
        return articulosRepository.findById(id);
    }
}
