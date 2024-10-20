package com.donactiva.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donactiva.proyecto.model.Articulos;
import com.donactiva.proyecto.model.Donacion;
import com.donactiva.proyecto.model.Puntos;
import com.donactiva.proyecto.model.Usuarios;
import com.donactiva.proyecto.repository.PuntosRepository;

interface InnerPuntosService {
    Iterable<Puntos> obtenerPuntosPorIdUsuario(int idUsuario);

    Puntos guardarPuntos(Usuarios usuario, Donacion donacion, Articulos articulo);
}

@Service
public class PuntosService implements InnerPuntosService {

    @Autowired
    private PuntosRepository puntosRepository;

    public Iterable<Puntos> obtenerPuntosPorIdUsuario(int idUsuario) {
        return puntosRepository.findAllByUsuario_IdUsuario(idUsuario);
    }

    public Puntos guardarPuntos(Usuarios usuario, Donacion donacion, Articulos articulo) {
        Puntos puntos = new Puntos();
        int cantidadPuntos = articulo.getNoPerecederos()
                + articulo.getHigiene()
                + articulo.getTextiles()
                + articulo.getJuguetes();
        puntos.setUsuario(usuario);
        puntos.setArticulo(articulo);
        puntos.setDonacion(donacion);
        puntos.setCantidad(cantidadPuntos);

        return puntosRepository.save(puntos);
    }
}
