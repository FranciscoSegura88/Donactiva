package com.donactiva.proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.donactiva.proyecto.model.Localizacion;

@Repository
public interface LocalizacionRepository extends CrudRepository<Localizacion, Integer>{

}
