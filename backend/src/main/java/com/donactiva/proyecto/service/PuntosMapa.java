package com.donactiva.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donactiva.proyecto.model.PuntoMapa;
import com.donactiva.proyecto.repository.PuntoMapaRepository;

interface PuntoMapaService {
    List<PuntoMapa> obtenerPuntosMapa();

    void guardarInstitucion(PuntoMapa puntoMapa);
}

@Service
public class PuntosMapa implements PuntoMapaService{

    @Autowired
    PuntoMapaRepository puntoMapaRepository;

    @Override
    public List<PuntoMapa> obtenerPuntosMapa() {
        return (List<PuntoMapa>) puntoMapaRepository.findAll();
    }

    @Override
    public void guardarInstitucion(PuntoMapa puntoMapa) {
        puntoMapaRepository.save(puntoMapa);
    }

}