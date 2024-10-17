package com.donactiva.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

import com.donactiva.proyecto.repository.DonacionRepository;

import jakarta.persistence.EntityNotFoundException;

import com.donactiva.proyecto.model.*;
import com.donactiva.proyecto.model.Donacion.EstadoDonacion;

interface InnerDonacionService {
    Donacion guardarDonacion(Donacion donacion, int idArticulo, int idLocalizacion, int idUsuario);
    Iterable<Donacion> obtenerTodasDonaciones(int idUsuario);
    Donacion marcarComoRecolectada(int id);
}

@Service
public class DonacionService implements InnerDonacionService{

    @Autowired
    private DonacionRepository donacionRepository;

    @Autowired
    private ArticulosService articulosService;

    @Autowired
    private LocalizacionService localizacionService;
    
    @Autowired
    private UsuarioService usuarioService;

    public Iterable<Donacion> obtenerTodasDonaciones(int idUsuario){
        return donacionRepository.findAllByUsuario_IdUsuario(idUsuario);
    }

    public Donacion guardarDonacion(Donacion donacion, int idArticulo, int idLocalizacion, int idUsuario){
        Articulos articulo = articulosService.obtenerArticulosPorId(idArticulo)
            .orElseThrow(() -> new RuntimeException("Artículo no encontrado con id: " + idArticulo));

        Localizacion localizacion = localizacionService.obtenerLocalizacionPorId(idLocalizacion)
            .orElseThrow(() -> new RuntimeException("Localización no encontrada con id: " + idLocalizacion));

        Usuarios usuario = usuarioService.obtenerUsuarioPorId(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idUsuario));

        donacion.setArticulo(articulo);
        donacion.setLocalizacion(localizacion);
        donacion.setUsuario(usuario);

        return donacionRepository.save(donacion);
    }

    @Scheduled(fixedRate = 60000) //cada un minuto para pruebas
    public void verificarCaducadas(){
        List<Donacion> donacionesPendientes = donacionRepository.findByEstado(EstadoDonacion.PENDIENTE);
        
        for(Donacion donacion : donacionesPendientes){
            if(donacion.getFechaCreacion().plusMinutes(2).isBefore(LocalDateTime.now())){
                donacion.setEstado(EstadoDonacion.CADUCADA);
                donacionRepository.save(donacion);
            }
        }
    }

    public Donacion marcarComoRecolectada(int id) {
    Donacion donacion = donacionRepository.findByIdDonacion(id);
    if (donacion != null) {
        donacion.setEstado(EstadoDonacion.RECOLECTADA);
        LocalDateTime fechaRecolectada = LocalDateTime.now();
        donacion.setFechaRecolectada(fechaRecolectada);
        return donacionRepository.save(donacion);
    }else{
        throw new EntityNotFoundException("Donación no encontrada con ID: " + id);
    }
    
}
}