package com.donactiva.proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.donactiva.proyecto.model.Articulos;

@Repository
public interface ArticulosRepository extends CrudRepository<Articulos, Integer>{

}
