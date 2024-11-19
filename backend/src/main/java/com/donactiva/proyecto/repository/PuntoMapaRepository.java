package com.donactiva.proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.donactiva.proyecto.model.PuntoMapa;

@Repository
public interface PuntoMapaRepository extends CrudRepository<PuntoMapa, Integer> {

}
