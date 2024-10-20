package com.donactiva.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.donactiva.proyecto.model.Usuarios;
import com.donactiva.proyecto.repository.UsuariosRepository;

interface UsuarioService {
    Usuarios obtenerUsuarioPorCorreo(String correo);

    Optional<Usuarios> obtenerUsuarioPorId(int id);

    void guardarUsuario(Usuarios usuario);

    void actualizarPuntos(Usuarios usuario, int cantidadPuntos, boolean operacion);

    List<Usuarios> listarUsuarios();

}

@Service
public class UsuariosService implements UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public Usuarios obtenerUsuarioPorCorreo(String correo) {
        return usuariosRepository.findByCorreo(correo);
    }

    @Override
    public void guardarUsuario(Usuarios usuario) {
        usuariosRepository.save(usuario);
    }

    @Override
    public void actualizarPuntos(Usuarios usuario, int cantidadPuntos, boolean operacion) {
        int puntosGanados = usuario.getPuntosGanados();
        int puntosUsados = usuario.getPuntosUsados();
        if (operacion == true) {
            puntosGanados += cantidadPuntos;
            usuario.setPuntosGanados(puntosGanados);
        } else {
            puntosUsados += cantidadPuntos;
            usuario.setPuntosUsados(puntosUsados);
        }
        usuario.setPuntosDisponibles(puntosGanados - puntosUsados);
        guardarUsuario(usuario);
    }

    @Override
    public List<Usuarios> listarUsuarios() {
        return (List<Usuarios>) usuariosRepository.findAll();
    }

    @Override
    public Optional<Usuarios> obtenerUsuarioPorId(int idUsuario) {
        return usuariosRepository.findById(idUsuario);
    }

}
