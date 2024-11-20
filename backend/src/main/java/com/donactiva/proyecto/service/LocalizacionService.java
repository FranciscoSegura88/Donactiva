package com.donactiva.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.donactiva.proyecto.repository.LocalizacionRepository;

import jakarta.persistence.EntityNotFoundException;

import com.donactiva.proyecto.model.Localizacion;

interface LocalizacionServiceInterface {
    Iterable<Localizacion> obtenerTodasLocalizaciones();
    Optional<Localizacion> obtenerLocalizacionPorId(int id);
    Localizacion guardarLocalizacion(Localizacion localizacion);
    Localizacion modificarLocalizacion(int idLocalizacion, Localizacion localizacion);
}

@Service
public class LocalizacionService implements LocalizacionServiceInterface {

    @Autowired
    private LocalizacionRepository localizacionRepository;

    @Override
    public Iterable<Localizacion> obtenerTodasLocalizaciones() {
        return localizacionRepository.findAll();
    }

    @Override
    public Optional<Localizacion> obtenerLocalizacionPorId(int id) {
        return localizacionRepository.findById(id);
    }

    @Override
    public Localizacion guardarLocalizacion(Localizacion localizacion) {
        return localizacionRepository.save(localizacion);
    }

    @Override
    public Localizacion modificarLocalizacion(int idLocalizacion, Localizacion localizacion){
        Localizacion locBusqueda = localizacionRepository.findByIdLocalizacion(idLocalizacion);
        if(locBusqueda != null && localizacion.getIdLocalizacion() == locBusqueda.getIdLocalizacion()){
            return localizacionRepository.save(localizacion);
        }else{
            throw new EntityNotFoundException("Localización no encontrada con ID: " + idLocalizacion);
        }
        
    }

}
