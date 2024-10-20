package com.donactiva.proyecto.repository;

import org.springframework.data.repository.CrudRepository;

import com.donactiva.proyecto.model.Puntos;

public interface PuntosRepository extends CrudRepository<Puntos, Integer> {
    Iterable<Puntos> findAllByDonacion_IdDonacion(int idDonacion);
    Iterable<Puntos> findAllByUsuario_IdUsuario(int idUsuario);
}
