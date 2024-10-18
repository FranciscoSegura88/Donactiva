package com.donactiva.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donactiva.proyecto.model.Articulos;
import com.donactiva.proyecto.model.Donacion;
import com.donactiva.proyecto.model.Puntos;
import com.donactiva.proyecto.model.Puntos.EstadoPuntos;
import com.donactiva.proyecto.repository.PuntosRepository;

interface InnerPuntosService {
    Iterable<Puntos> obtenerTodosPuntos(int idUsuario);
    Iterable<Puntos> obtenerPuntosPorEstado(int idUsuario, EstadoPuntos estadoPuntos);
    Puntos guardarPuntos (Donacion donacion, Articulos articulo);
}

@Service
public class PuntosService implements InnerPuntosService{

    @Autowired
    private PuntosRepository puntosRepository;

    public Iterable<Puntos> obtenerTodosPuntos(int idDonacion){
        return puntosRepository.findAllByDonacion_IdDonacion(idDonacion);
    }

    public Iterable<Puntos> obtenerPuntosPorEstado(int idDonacion, EstadoPuntos estadoPuntos){
        return puntosRepository.findAllByDonacion_IdDonacionAndEstado(idDonacion, estadoPuntos);
    }

    public Puntos guardarPuntos (Donacion donacion, Articulos articulo){
        Puntos puntos = new Puntos(); 
        int cantidadPuntos = articulo.getNoPerecederos() 
                                + articulo.getHigiene() 
                                + articulo.getTextiles() 
                                + articulo.getJuguetes();

        puntos.setArticulo(articulo);
        puntos.setDonacion(donacion);
        puntos.setCantidad(cantidadPuntos);
        
        return puntosRepository.save(puntos);
    }
}
