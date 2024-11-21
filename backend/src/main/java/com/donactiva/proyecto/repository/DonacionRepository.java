package com.donactiva.proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.donactiva.proyecto.model.Donacion;
import com.donactiva.proyecto.model.Donacion.EstadoDonacion;

import java.util.List;
import java.util.Optional;


@Repository
public interface DonacionRepository extends CrudRepository<Donacion, Integer>{
    Optional<Donacion> findById(int idDonacion);
    Donacion findByIdDonacion(int idDonacion);
    List<Donacion> findByEstado(EstadoDonacion estadoDonacion);
    Iterable<Donacion> findAllByUsuario_IdUsuario(int idUsuario);
}
