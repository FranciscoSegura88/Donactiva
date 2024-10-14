package com.donactiva.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.donactiva.proyecto.model.Usuarios;
import com.donactiva.proyecto.repository.UsuariosRepository;


interface UsuarioService {
    Usuarios obtenerUsuarioPorCorreo(String correo);
    void guardarUsuario(Usuarios usuario);
    List<Usuarios> listarUsuarios();
}

@Service
public class UsuariosService implements UsuarioService {

    @Autowired
    private UsuariosRepository usuarioRepository;

    @Override
    public Usuarios obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public void guardarUsuario(Usuarios usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuarios> listarUsuarios() {
        return (List<Usuarios>) usuarioRepository.findAll();
    }

}