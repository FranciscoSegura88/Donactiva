package com.donactiva.proyecto.repository;

import org.springframework.data.repository.CrudRepository;

import com.donactiva.proyecto.model.Puntos;
import com.donactiva.proyecto.model.Puntos.EstadoPuntos;

public interface PuntosRepository extends CrudRepository<Puntos, Integer> {
    Iterable<Puntos> findAllByDonacion_IdDonacion(int idDonacion);
    Iterable<Puntos> findAllByDonacion_IdDonacionAndEstado(int idDonacion, EstadoPuntos estadoPuntos);
}
