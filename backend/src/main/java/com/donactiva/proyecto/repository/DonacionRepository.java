package com.donactiva.proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.donactiva.proyecto.model.Donacion;

@Repository
public interface DonacionRepository extends CrudRepository<Donacion, Integer>{

}
