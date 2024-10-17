package com.donactiva.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donactiva.proyecto.repository.DonacionRepository;
import com.donactiva.proyecto.model.*;

interface InnerDonacionService {
    Donacion guardarDonacion(Donacion donacion, int idArticulo, int idLocalizacion, int idUsuario);
    Iterable<Donacion> obtenerTodasDonaciones(int idUsuario);
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
}
