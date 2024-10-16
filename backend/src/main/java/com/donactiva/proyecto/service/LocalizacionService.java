package com.donactiva.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.donactiva.proyecto.repository.LocalizacionRepository;
import com.donactiva.proyecto.model.Localizacion;

interface LocalizacionServiceInterface{
    List<Localizacion> obtenerTodasLocalizaciones();
    Optional<Localizacion> obtenerLocalizacionPorId(int id);
}

@Service
public class LocalizacionService implements LocalizacionServiceInterface{


    @Autowired
    private LocalizacionRepository localizacionRepository;

    @Override
    public List<Localizacion> obtenerTodasLocalizaciones(){
        return (List<Localizacion>) localizacionRepository.findAll();
    }

    @Override
    public Optional<Localizacion> obtenerLocalizacionPorId(int id) {
        return localizacionRepository.findById(id);
    }
}
