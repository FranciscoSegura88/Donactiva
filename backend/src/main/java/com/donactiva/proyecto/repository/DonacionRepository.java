package com.donactiva.proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.donactiva.proyecto.model.Donacion;
import com.donactiva.proyecto.model.Donacion.EstadoDonacion;

import java.util.List;


@Repository
public interface DonacionRepository extends CrudRepository<Donacion, Integer>{
    Iterable<Donacion> findAllByUsuario_IdUsuario(int idUsuario);
    List<Donacion> findByEstado(EstadoDonacion estado);
    Donacion findByIdDonacion(int idDonacion);
}
