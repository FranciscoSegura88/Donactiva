package com.donactiva.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donactiva.proyecto.model.Canjes;
import com.donactiva.proyecto.model.Usuarios;
import com.donactiva.proyecto.repository.CanjesRepository;

interface InnerCanjesService {
    Canjes canjearPuntos(Canjes canje, int idUsuario, int cantidadPuntos, String convenio);
}

@Service
public class CanjesService implements InnerCanjesService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CanjesRepository canjesRepository;

    public Canjes canjearPuntos(Canjes canje, int idUsuario, int cantidadPuntos, String convenio) {
        boolean operacion = false;
        Usuarios usuario = usuarioService.obtenerUsuarioPorId(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idUsuario));
        if (cantidadPuntos <= usuario.getPuntosDisponibles()) {
            
            usuarioService.actualizarPuntos(usuario, cantidadPuntos, operacion);
            canje.setPuntosCanjeados(cantidadPuntos);
            canje.setUsuario(usuario);
            canje.setConvenio(convenio);
            return canjesRepository.save(canje);
        }
        throw new RuntimeException("El usuario no tiene suficientes puntos disponibles.");

    }
}
